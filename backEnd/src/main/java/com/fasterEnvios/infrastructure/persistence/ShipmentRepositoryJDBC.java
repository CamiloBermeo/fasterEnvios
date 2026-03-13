package com.fasterEnvios.infrastructure.persistence;

import com.fasterEnvios.domain.model.PackageModel;
import com.fasterEnvios.domain.model.PaymentTransaction;
import com.fasterEnvios.domain.model.Shipment;
import com.fasterEnvios.domain.repository.ShipmentRepository;
import com.fasterEnvios.infrastructure.controller.ShipmentController;
import com.fasterEnvios.infrastructure.entity.PackageEntity;
import com.fasterEnvios.infrastructure.entity.PaymentTransactionEntity;
import com.fasterEnvios.infrastructure.entity.ShipmentEntity;
import com.fasterEnvios.infrastructure.mapper.PackageInfraMapper;
import com.fasterEnvios.infrastructure.mapper.PaymentMethodInfraMapper;
import com.fasterEnvios.infrastructure.mapper.PaymentTransactionInfraMapper;
import com.fasterEnvios.infrastructure.mapper.ShipmentInfraMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class ShipmentRepositoryJDBC implements ShipmentRepository {
    private final JdbcTemplate jdbcTemplate;
    private final PackageInfraMapper packageInfraMapper;
    private final PaymentTransactionInfraMapper paymentTransactionInfraMapper;
    private final PaymentMethodInfraMapper paymentMethodInfraMapper;

    @Override
    @Transactional
    public Shipment save(Shipment shipment) {

        ShipmentEntity shipmentEntity = ShipmentInfraMapper.toEntity(shipment,
                packageInfraMapper.toEntityList(shipment.getPackageModels()),
                paymentTransactionInfraMapper.toEntity(shipment.getPaymentTransaction(),
                        paymentMethodInfraMapper.listToEntity(shipment.getPaymentTransaction().getPaymentMethods())));

        Long paymentId = savePayment(shipmentEntity.getPaymentTransaction());
        Long shipmentId = saveShipmentHeader(shipmentEntity, paymentId);

        savePackages(shipmentEntity.getPackages(), shipmentId);

        return ShipmentInfraMapper.toModel(shipmentEntity,
                shipmentId,
                packageInfraMapper.toModelList(shipmentEntity.getPackages()),
                paymentTransactionInfraMapper.toModel(shipmentEntity.getPaymentTransaction(),
                        paymentId,
                        paymentMethodInfraMapper.toModelList(shipmentEntity.getPaymentTransaction().getPaymentMethods())
                ));

    }

    private Long savePayment(PaymentTransactionEntity payment) {
        String sql = "INSERT INTO payment_transactions (amount, payment_date, payment_methods,payment_status) VALUES (?, ?, ?,? )";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setBigDecimal(1, payment.getAmount());
            ps.setObject(2, payment.getPaymentMethods());
            ps.setTimestamp(3,Timestamp.valueOf(payment.getPaymentDate()));
            ps.setObject(4, payment.getPaymentStatus());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }
    private Long saveShipmentHeader(ShipmentEntity shipment, Long paymentId) {
        String sql = "INSERT INTO shipments ( created_at,estimated_delivery_date, city_origin, city_destination, total_amount, distance, state, payment_transaction_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, paymentId);
            ps.setObject(2, shipment.getCityOrigin());
            ps.setObject(3, shipment.getCityDestination());
            ps.setDouble(4, shipment.getDistance());
            ps.setObject(5, shipment.getState());
            ps.setTimestamp(6, Timestamp.valueOf(shipment.getCreatedAt()));
            ps.setTimestamp(7, Timestamp.valueOf(shipment.getEstimatedDeliveryDate()));
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    private void savePackages(List<PackageEntity> packages, Long shipmentId) {
        String sql = "INSERT INTO packages (shipment_id, weight_kg, dimensions, declared_value, description) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                PackageEntity pkg = packages.get(i);
                ps.setLong(1, shipmentId);
                ps.setDouble(2, pkg.getWeightKg());
                ps.setDouble(3, pkg.getDimensions());
                ps.setBigDecimal(4, pkg.getDeclaredValue());
                ps.setString(5, pkg.getDescription());
            }

            @Override
            public int getBatchSize() {
                return packages.size();
            }
        });
    }
}

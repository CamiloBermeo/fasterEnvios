package com.fasterEnvios.infrastructure.persistence;

import com.fasterEnvios.domain.model.Shipment;
import com.fasterEnvios.domain.repository.ShipmentRepository;
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
        String sql = "INSERT INTO payment_transactions (amount, payment_date,payment_status) VALUES (?, ?, ? )";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setBigDecimal(1, payment.getAmount());
            ps.setTimestamp(2, Timestamp.valueOf(payment.getPaymentDate()));
            ps.setObject(3, payment.getPaymentStatus());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    private Long saveShipmentHeader(ShipmentEntity shipment, Long paymentId) {
        String sql = "INSERT INTO shipments ( created_at, estimated_delivery_date, city_origin, city_destination, total_amount, distance, state, payment_transaction_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, Timestamp.valueOf(shipment.getCreatedAt()));
            ps.setTimestamp(2, Timestamp.valueOf(shipment.getEstimatedDeliveryDate()));
            ps.setLong(3, shipment.getCityOrigin().getId());
            ps.setLong(4, shipment.getCityDestination().getId());
            ps.setObject(5, shipment.getTotalAmount());
            ps.setDouble(6, shipment.getDistance());
            ps.setObject(7, shipment.getState());
            ps.setLong(8, paymentId);

            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    private void savePackages(List<PackageEntity> packages, Long shipmentId) {
        String sqlPackage = "INSERT INTO package_models (weight_kg, dimensions, declared_value, description) VALUES (?, ?, ?, ?)";
        String sqlRelation = "INSERT INTO shipment_packages (shipment_id, package_id) VALUES (?, ?)";

        for (PackageEntity pkg : packages) {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sqlPackage, Statement.RETURN_GENERATED_KEYS);
                ps.setDouble(1, pkg.getWeightKg());
                ps.setDouble(2, pkg.getDimensions());
                ps.setBigDecimal(3, pkg.getDeclaredValue());
                ps.setString(4, pkg.getDescription());
                return ps;
            }, keyHolder);

            Long packageId = Objects.requireNonNull(keyHolder.getKey()).longValue();

            jdbcTemplate.update(sqlRelation, shipmentId, packageId);
        }
    }
}

package com.bitresolution.cep.application.engine;

import com.bitresolution.cep.application.partitions.CepPartition;

public class PartitionAlreadyExistsException extends RuntimeException {
    public PartitionAlreadyExistsException(CepPartition partition) {
        super("Error updating partition " + partition.getName());
    }
}

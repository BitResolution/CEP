package com.bitresolution.cep.application.engine;

import com.bitresolution.cep.application.partitions.CepPartition;

public class PartitionDoesNotExistException extends RuntimeException {

    public PartitionDoesNotExistException(CepPartition partition) {
        super("Partition '" + partition.getName() + "' does not exist");
    }
}

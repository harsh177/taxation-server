package com.taxation.service.interfaces;

import com.taxation.model.Tax;

import java.util.List;

public interface ITaxService {

    public void saveAll(List<Tax> taxList);

    public Tax getTaxForWaterConnectedProperty();

    public Tax getTaxForWithoutWaterConnectionProperty();
}

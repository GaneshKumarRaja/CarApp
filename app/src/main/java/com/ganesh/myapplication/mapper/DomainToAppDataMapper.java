package com.ganesh.myapplication.mapper;

import com.ganesh.domain.common.Mapper;
import com.ganesh.domain.model.CarsLocationDomainModel;
import com.ganesh.myapplication.model.MarkerModel;

public class DomainToAppDataMapper extends Mapper<CarsLocationDomainModel, MarkerModel> {

    @Override
    public MarkerModel mapFrom(CarsLocationDomainModel model) {
        return new MarkerModel(Double.parseDouble(model.getLat()), Double.parseDouble(model.getLon()));
    }
}

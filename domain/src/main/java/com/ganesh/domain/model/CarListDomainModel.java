package com.ganesh.domain.model;
//
// Created by  on 2020-01-03.
//

import com.ganesh.domain.common.DomainMapper;

import java.util.List;

public class CarListDomainModel implements DomainMapper {
    List<CarsLocationDomainModel> modelList;

    public CarListDomainModel(List<CarsLocationDomainModel> modelList){
        this.modelList=modelList;
    }
}



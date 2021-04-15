package com.siy.KitMarket.domain.dto.post.detail;

import com.querydsl.core.annotations.QueryProjection;
import com.siy.KitMarket.domain.dto.account.AccountDto;
import com.siy.KitMarket.domain.dto.post.ApplicationDto;
import com.siy.KitMarket.domain.entity.post.CarFull;
import com.siy.KitMarket.domain.entity.post.Post;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Data
@NoArgsConstructor
public class CarFoolDtoDetail extends PostDtoDetail{
    private String carFool;

    @QueryProjection
    public CarFoolDtoDetail(CarFull carFull, Set<AccountDto> participants, Set<ApplicationDto> applications){
        super(carFull, participants, applications);
        this.carFool = carFull.getCategory();
    }




}

package com.quentin.example.service.impl;

import com.quentin.example.domain.OptEwbVO;
import com.quentin.example.domain.mapper.OptEwbVOMapper;
import com.quentin.example.service.IEwbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auth Created by guoqun.yang
 * @Date Created in 17:08 2018/1/25
 * @Version 1.0
 */
@Service
public class EwbServiceImpl implements IEwbService{

    @Autowired
    private OptEwbVOMapper optEwbVOMapper;

    @Override
    public OptEwbVO getEwb(String ewbNo) {
        return optEwbVOMapper.selectByEwbNo(ewbNo);
    }
}

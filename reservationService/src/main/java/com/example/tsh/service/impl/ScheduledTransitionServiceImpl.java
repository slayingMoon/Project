package com.example.tsh.service.impl;

import com.example.tsh.model.entity.ScheduledTransition;
import com.example.tsh.service.ScheduledTransitionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ScheduledTransitionServiceImpl extends GenericServiceImpl<ScheduledTransition> implements ScheduledTransitionService {


}

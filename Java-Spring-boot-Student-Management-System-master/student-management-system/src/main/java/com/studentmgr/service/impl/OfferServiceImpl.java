package com.studentmgr.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmgr.common.service.impl.GenericServiceImpl;
import com.studentmgr.dao.OfferDao;
import com.studentmgr.model.Offer;
import com.studentmgr.service.OfferService;

@Service
public class OfferServiceImpl extends GenericServiceImpl<Offer> implements OfferService {

	@Autowired
	protected OfferDao offerDao;
	
	@PostConstruct
	void init() {
		init(Offer.class, offerDao);
	}
}

package com.studentmgr.dao.impl;

import org.springframework.stereotype.Repository;

import com.studentmgr.common.dao.impl.GenericDaoImpl;
import com.studentmgr.dao.OfferDao;
import com.studentmgr.model.Offer;

@Repository
public class OfferDaoImpl extends GenericDaoImpl<Offer> implements OfferDao {

	public OfferDaoImpl() {
		super(Offer.class);
	}

}

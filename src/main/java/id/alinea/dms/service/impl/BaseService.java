package id.alinea.dms.service.impl;

import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import id.alinea.dms.service.IRepositoryLocator;
import id.alinea.dms.service.IServiceLocator;

public abstract class BaseService {

    protected Logger logger = LoggerFactory.getLogger(BaseService.class);
    protected Gson gson = new Gson();
    
    @Autowired
    protected IRepositoryLocator repositoryLocator;
    
    @Autowired
    protected IServiceLocator serviceLocator;
}

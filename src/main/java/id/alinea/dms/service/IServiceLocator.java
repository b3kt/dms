package id.alinea.dms.service;

public interface IServiceLocator {

    IStorageService getStorageService();

    IDbSystemFileService getSystemFileService(); 

    IImageKitService getImageKitService();
    
}

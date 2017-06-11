package pe.edu.utp.hremployees.models;

import java.sql.Connection;
import java.util.List;

/**
 * Created by GrupoUTP on 10/06/2017.
 */
public class HRDataStore {
    private Connection connection;
    private RegionsEntity regionsEntity;
    private CountriesEntity countriesEntity;
    private LocationsEntity locationsEntity;

    public HRDataStore(Connection connection) {
        this.connection = connection;
    }

    public HRDataStore() {
    }

    public Connection getConnection() {
        return connection;
    }

    public HRDataStore setConnection(Connection connection) {
        this.connection = connection;
        return this;
    }

    private RegionsEntity getRegionsEntity() {
        if(regionsEntity == null) {
            regionsEntity = new RegionsEntity(getConnection());
        }
        return regionsEntity;
    }

    public List<Region> findAllRegions() {
        return getRegionsEntity().findAll();
    }

    public boolean createRegion(Region region) {
        return getRegionsEntity().add(region);
    }

    private CountriesEntity getCountriesEntity() {
        if(countriesEntity == null) {
            countriesEntity = new CountriesEntity(getConnection());
        }
        return countriesEntity;
    }

    public List<Country> findAllCountries() {
        return getCountriesEntity()
                .findAll(getRegionsEntity());
    }

    private LocationsEntity getLocationsEntity() {
        if(locationsEntity == null) {
            locationsEntity = new LocationsEntity(getConnection());
        }
        return locationsEntity;
    }

    public List<Location> findAllLocations() {
        return getLocationsEntity().findAll(getCountriesEntity(),
                getRegionsEntity());
    }


}

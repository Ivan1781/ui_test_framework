package de.org.properties;

public interface Props {

    Boolean IS_REMOTE_RUN = Boolean.parseBoolean(PropertiesLoader.getProperty("is_remote_run"));

}

package xyz.lotho.me.storage.utils;

import java.sql.ResultSet;

public interface SelectCall {
    void call(ResultSet resultSet);
}

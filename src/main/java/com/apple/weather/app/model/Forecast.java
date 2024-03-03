package com.apple.weather.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Forecast {

    private Number ts;
    private String timestamp_local;
    private LocalDateTime timestamp_utc;
    private String datetime;
    private int snow;
    private Number snow_depth;
    private Number precip;
    @JsonProperty(index = 1)
    private Number temp;
    private Number dewpt;
    @JsonProperty(index = 2)
    private Number max_temp;
    @JsonProperty(index = 3)
    private Number min_temp;
    private Number app_max_temp;
    private Number app_min_temp;
    private int rh;
    private int clouds;
    private Weather weather;
    private Number slp;
    private Number pres;
    private Number uv;
    private Number max_dhi;
    private Number vis;
    private Number pop;
    private Number moon_phase;
    private int sunrise_ts;
    private int sunset_ts;
    private int moonrise_ts;
    private int moonset_ts;
    private String pod;
    private Number wind_spd;
    private int wind_dir;
    private String wind_cdir;
    private String wind_cdir_full;
}

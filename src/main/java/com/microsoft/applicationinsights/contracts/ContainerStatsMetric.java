/*
 * ApplicationInsights-Docker
 * Copyright (c) Microsoft Corporation
 * All rights reserved.
 *
 * MIT License
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the ""Software""), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 * persons to whom the Software is furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED *AS IS*, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

package com.microsoft.applicationinsights.contracts;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.applicationinsights.common.Constants;

/**
 * Created by yonisha on 7/22/2015.
 */
public class ContainerStatsMetric {

    // region Members

    private String dockerHost;
    private String dockerImage;
    private String dockerContainerName;
    private String dockerContainerId;

    private String metricName;
    private double value;
    private int count;
    private double min;
    private double max;
    private double stdDev;

    // endregion Members

    // region Ctor

    public ContainerStatsMetric(String json) {
        deserialize(json);
    }

    // endregion Ctor

    // region Public Methods

    public String getDockerHost() {
        return dockerHost;
    }

    public String getDockerImage() {
        return dockerImage;
    }

    public String getDockerContainerName() {
        return dockerContainerName;
    }

    public String getDockerContainerId() {
        return dockerContainerId;
    }

    public String getMetricName() {
        return metricName;
    }

    public double getValue() {
        return value;
    }

    public int getCount() {
        return count;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public double getStdDev() {
        return stdDev;
    }

    // endregion Public Methods

    // region Private Methods

    private void deserialize(String json) {
        JsonObject jsonObj = new JsonParser().parse(json).getAsJsonObject();
        JsonObject metricJson = jsonObj.getAsJsonObject("metric");

        this.metricName = metricJson.get("name").getAsString();
        this.value = metricJson.get("value").getAsDouble();
        this.count = metricJson.get("count").getAsInt();
        this.min = metricJson.get("min").getAsDouble();
        this.max = metricJson.get("max").getAsDouble();
        this.stdDev = metricJson.get("std").getAsDouble();

        JsonObject propertiesJson = jsonObj.getAsJsonObject("properties");
        this.dockerHost = propertiesJson.get(Constants.DOCKER_HOST_PROPERTY_KEY).getAsString();
        this.dockerImage = propertiesJson.get(Constants.DOCKER_IMAGE_PROPERTY_KEY).getAsString();
        this.dockerContainerName = propertiesJson.get(Constants.DOCKER_CONTAINER_NAME_PROPERTY_KEY).getAsString();
        this.dockerContainerId = propertiesJson.get(Constants.DOCKER_CONTAINER_ID_PROPERTY_KEY).getAsString();
    }

    // endregion Private Methods
}
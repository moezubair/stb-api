package com.stickyblueteam.traffic.service;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.bigquery.*;
import com.stickyblueteam.traffic.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Muhammad Zubair <mzubair.ca> on 12/5/2018.
 */
@Service
public class QueryService {
    @Autowired
    private Environment environment;
    @Value("${query.key}")
    private String key;

    public List<Weather> getWeather() throws InterruptedException, IOException {
        BigQuery bigquery = BigQueryOptions.newBuilder().setProjectId("stickybluenotes")
                .setCredentials(
                        ServiceAccountCredentials.fromStream(new FileInputStream(key))
                ).build().getService();
        QueryJobConfiguration queryConfig =
                QueryJobConfiguration.newBuilder(
                        "SELECT  * FROM `stickybluenotes.geotab_public_datesets.TorontoWeather` LIMIT 1000")
                        // Use standard SQL syntax for queries.
                        // See: https://cloud.google.com/bigquery/sql-reference/
                        .setUseLegacySql(false)
                        .build();

        // Create a job ID so that we can safely retry.
        JobId jobId = JobId.of(UUID.randomUUID().toString());
        Job queryJob = bigquery.create(JobInfo.newBuilder(queryConfig).setJobId(jobId).build());

        // Wait for the query to complete.
        queryJob = queryJob.waitFor();

        // Check for errors
        if (queryJob == null) {
            throw new RuntimeException("Job no longer exists");
        } else if (queryJob.getStatus().getError() != null) {
            // You can also look at queryJob.getStatus().getExecutionErrors() for all
            // errors, not just the latest one.
            throw new RuntimeException(queryJob.getStatus().getError().toString());
        }

        // Get the results.
        QueryResponse response = bigquery.getQueryResults(jobId);

        TableResult result = queryJob.getQueryResults();
        List<Weather> weather = new ArrayList<>();

        // Print all pages of the results.
        for (FieldValueList row : result.iterateAll()) {

            weather.add(new Weather(row.get("sw_lon").getStringValue(), row.get("sw_lat").getStringValue(),
                    row.get("Temperature").getDoubleValue(), row.get("Hour").getStringValue()));

        }

        return weather;
    }
}

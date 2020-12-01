/*
 * Copyright 2020 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 *
 */
package com.amazon.opendistroforelasticsearch.reportsscheduler.resthandler

import com.amazon.opendistroforelasticsearch.reportsscheduler.ReportsSchedulerPlugin.Companion.BASE_REPORTS_URI
import com.amazon.opendistroforelasticsearch.reportsscheduler.action.ReportDefinitionActions
import org.elasticsearch.client.node.NodeClient
import org.elasticsearch.rest.BaseRestHandler
import org.elasticsearch.rest.BaseRestHandler.RestChannelConsumer
import org.elasticsearch.rest.BytesRestResponse
import org.elasticsearch.rest.RestHandler.Route
import org.elasticsearch.rest.RestRequest
import org.elasticsearch.rest.RestRequest.Method.GET
import org.elasticsearch.rest.RestStatus

/**
 * Rest handler for getting stats.
 * TODO: change this This handler uses [ReportDefinitionActions].
 */
internal class ReportStatsHandler : BaseRestHandler() {
    companion object {
        private const val REPORT_STATS_ACTION = "report_stats_actions"
        private const val REPORT_STATS_URL = "$BASE_REPORTS_URI/stats"
    }

    /**
     * {@inheritDoc}
     */
    override fun getName(): String {
        return REPORT_STATS_ACTION
    }

    /**
     * {@inheritDoc}
     */
    override fun routes(): List<Route> {
        return listOf(
            /**
             * Get all reporting stats
             * Request URL: GET REPORT_STATS_URL
             * Request body: None
             * TODO: change this Response body: Ref [com.amazon.opendistroforelasticsearch.reportsscheduler.model.GetAllReportDefinitionsResponse]
             */
            Route(GET, REPORT_STATS_URL)
        )
    }

    /**
     * {@inheritDoc}
     */
    override fun prepareRequest(request: RestRequest, client: NodeClient): RestChannelConsumer {
        return when (request.method()) {
            GET -> RestChannelConsumer {
                it.sendResponse(BytesRestResponse(RestStatus.OK, "{request_count : 15}"))
            }
            else -> RestChannelConsumer {
                it.sendResponse(BytesRestResponse(RestStatus.METHOD_NOT_ALLOWED, "${request.method()} is not allowed"))
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    override fun responseParams(): Set<String> {
        return setOf()
    }
}

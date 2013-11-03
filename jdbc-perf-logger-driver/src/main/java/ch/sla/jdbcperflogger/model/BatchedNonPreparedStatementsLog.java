/* 
 *  Copyright 2013 Sylvain LAURENT
 *     
 *  Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.sla.jdbcperflogger.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.annotation.ParametersAreNonnullByDefault;

import ch.sla.jdbcperflogger.StatementType;

@ParametersAreNonnullByDefault
public class BatchedNonPreparedStatementsLog extends AbstractBeforeStatementExecutionLog {

    private static final long serialVersionUID = 1L;

    private final List<String> sqlList;

    public BatchedNonPreparedStatementsLog(final int connectionId, final UUID logId, final long timestamp,
            final List<String> sqlList, final String threadName) {
        super(connectionId, logId, timestamp, StatementType.NON_PREPARED_BATCH_EXECUTION, threadName);
        this.sqlList = Collections.unmodifiableList(new ArrayList<String>(sqlList));
    }

    public List<String> getSqlList() {
        return sqlList;
    }

}

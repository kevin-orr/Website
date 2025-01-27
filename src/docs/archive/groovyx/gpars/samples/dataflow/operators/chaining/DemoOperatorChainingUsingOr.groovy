// GPars - Groovy Parallel Systems
//
// Copyright © 2008-11  The original author or authors
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package groovyx.gpars.samples.dataflow.operators.chaining

import groovyx.gpars.dataflow.DataflowQueue

/**
 * The | operator can be used as a replacement for the chainWith() method and allows you to build pipe-lines off the original channel.
 * The type of the channel gets preserved across the whole chain.
 *
 * @author Vaclav Pech
 */

final DataflowQueue queue = new DataflowQueue()
queue | {it * 2} | {it + 1} | {println it}

queue << 1
queue << 2
queue << 3
queue << 4
queue << 5

sleep 1000


// GPars - Groovy Parallel Systems
//
// Copyright © 2008-10  The original author or authors
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

package groovyx.gpars.samples.agent

import groovyx.gpars.agent.Agent

/**
 * A thread-safe counter. Threads can submit commands, which increase or decrease the internal counter without fear
 * of mutual races or lost updates.
 */
final Agent counter = new Agent<Long>(0L)

final Thread t1 = Thread.start {
    counter << {updateValue it + 1}
}

final Thread t2 = Thread.start {
    counter << {updateValue it + 6}
}

final Thread t3 = Thread.start {
    counter << {updateValue it - 2}
}

[t1, t2, t3]*.join()

assert 5 == counter.val

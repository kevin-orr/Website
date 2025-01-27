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

package groovyx.gpars.samples.actors.stateful

import groovyx.gpars.actor.Actors

/**
 * Creates a chain of actors, which pass a message from one end of the chain to the other one.
 */

def intermediaries = []

for (i in 1..30) {
    intermediaries << Actors.actor({index ->
        react {
            println('Received: ' + index + ":" + it)
            if (index != 29) intermediaries[index + 1].send('message')
        }
    }.curry(i))
}

intermediaries[0].send 'message'
sleep 2000
// GPars - Groovy Parallel Systems
//
// Copyright © 2008-11  The original author or authors
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package groovyx.gpars.samples.forkjoin

import groovyx.gpars.GParsPool
import static groovyx.gpars.GParsPool.withPool

/**
 * The simplest possible parallel implementation of naive recursive Fibonacci number calculator
 */

withPool {
    println GParsPool.runForkJoin(30) {n ->
        if (n <= 2) return 1
        forkOffChild(n - 2)
        forkOffChild(n - 1)
        getChildrenResults().sum()
    }
}
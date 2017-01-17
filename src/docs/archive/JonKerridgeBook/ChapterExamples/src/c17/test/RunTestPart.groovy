// GPars (formerly GParallelizer)
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

package c17.test

import org.jcsp.lang.*
import org.jcsp.groovy.*
import org.jcsp.net.*
import org.jcsp.net.cns.*
import org.jcsp.net.tcpip.*
import org.jcsp.groovy.util.*


class RunTestPart extends GroovyTestCase {

    void testSomething() {

        Node.info.setDevice(null)

        Node.getInstance().init(new TCPIPNodeFactory())

        NetChannelOutput ordinaryInput = CNS.createOne2Net("ordinaryInput")
        NetChannelInput scaledOutput = CNS.createNet2One("scaledOutput")

        def collector = new CollectNumbers(inChannel: scaledOutput)
        def generator = new GenerateNumbers(outChannel: ordinaryInput)

        def testList = [collector, generator]

        new PAR(testList).run()

        def original = generator.generatedList
        def unscaled = collector.collectedList
        def scaled = collector.scaledList
        assertTrue(original == unscaled)
        assertTrue(TestUtilities.list1GEList2(scaled, original))

    }

}

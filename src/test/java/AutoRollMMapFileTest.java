
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Created by CuttleFish on 2020/7/12.
 */

import com.github.jobop.gekko.protocols.message.GekkoEntry;
import com.github.jobop.gekko.store.mmap.AutoRollMMapFile;
import com.github.jobop.gekko.store.mmap.SequenceFile;
import com.github.jobop.gekko.utils.CodecUtils;
import org.junit.Assert;
import org.junit.Test;

import java.nio.ByteBuffer;

public class AutoRollMMapFileTest {
    @Test
    public void testNewAAutoRollMappFile() {

        SequenceFile file = new AutoRollMMapFile("/Users/zhengwei/Desktop/autorollfiles", 1024 * 1024, 1024 * 4);
        byte[] bytes = "asdfasdfasdfasdfasdfasdfasdfadfasdfasdfasdfasdfasdfasdfasdfadfasdfasdfasdfasdfasdfasdfasdfadfasdfasdfasdfasdfasdfasdfasdfadfasdfasdfasdfasdfasdfasdfasdfadfasdfasdfasdfasdfasdfasdfasdfadf".getBytes();
        System.out.println();
        double totallength = 0;
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 10000000; i++) {
            file.appendMessage(bytes);
            totallength += bytes.length;
        }
        System.out.println("每个数据大小=" + bytes.length+" 写入10000000次"+" 总数据大小=" + totallength / 1024 / 1024 / 1024 + "G " + "耗时=" + (System.currentTimeMillis() - startTime)+"ms");


    }

    @Test
    public void testLoadAAutoRollMappFile() {

        AutoRollMMapFile file = new AutoRollMMapFile("/Users/zhengwei/Desktop/autorollfiles", 1024 * 1024, 1024 * 4);
        file.load();
        Assert.assertTrue(file.checksum());

    }
}
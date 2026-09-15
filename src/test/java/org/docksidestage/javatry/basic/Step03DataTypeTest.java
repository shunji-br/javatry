/*
 * Copyright 2019-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.javatry.basic;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of data type. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author shunji suzuki
 */
public class Step03DataTypeTest extends PlainTestCase {

    // ===================================================================================
    //                                                                          Basic Type
    //                                                                          ==========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_datatype_basicType() {
        String sea = "mystic";
        Integer land = 416;
        LocalDate piari = LocalDate.of(2001, 9, 4); // immutable
        LocalDateTime bonvo = LocalDateTime.of(2001, 9, 4, 12, 34, 56); // immutable
        Boolean dstore = true;
        BigDecimal amba = new BigDecimal("9.4");

        piari = piari.plusDays(1); // instance
        land = piari.getYear();
        bonvo = bonvo.plusMonths(1); // instance
        land = bonvo.getMonthValue();
        land--; // -1
        if (dstore) {
            BigDecimal addedDecimal = amba.add(new BigDecimal(land)); // amba + land
            sea = String.valueOf(addedDecimal);
        }
        log(sea); // your answer? => 18.4 | o
    }

    // ===================================================================================
    //                                                                           Primitive
    //                                                                           =========
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_datatype_primitive() {
        byte sea = 127; // -128 〜 127
        short land = 32767; // -32,768 〜 32,767
        int piari = 1;
        long bonvo = 9223372036854775807L; // max
        float dstore = 1.1f;
        double amba = 2.3d;
        char miraco = 'a';
        boolean dohotel = miraco == 'a'; // true
        if (dohotel && dstore >= piari) { // true && (1.1 >= 1) -> true | widening
            bonvo = sea; // 127 | widening
            land = (short) bonvo; // 127 | narrowing
            bonvo = piari; // 1 | widening
            sea = (byte) land; // 127 | narrowing
            if (amba == 2.3D) { // d Dは同じ
                sea = (byte) amba; // (byte) 2.3 -> 2 | narrowing
            }
        }
        if ((int) dstore > piari) { // (int) 1.1 -> 1, 1 > 1 -> false
            sea = 0;
        }
        log(sea); // your answer? => 2 | o
    }

    // ===================================================================================
    //                                                                              Object
    //                                                                              ======
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_datatype_object() {
        St3ImmutableStage stage = new St3ImmutableStage("hangar"); // stageName = "hangar"
        String sea = stage.getStageName(); // "hangar"
        log(sea); // your answer? => hangar | o
    }

    private static class St3ImmutableStage {

        private final String stageName;

        // コンストラクタ
        // コンストラクタと判定される条件は2つとも満たす必要がある。
        // 1. メソッド名がクラス名と完全に一致している
        // 2. 戻り値の型が一切書かれていない（voidすら書かない）
        public St3ImmutableStage(String stageName) {
            this.stageName = stageName;
        }

        public String getStageName() {
            return stageName;
        }
    }
}

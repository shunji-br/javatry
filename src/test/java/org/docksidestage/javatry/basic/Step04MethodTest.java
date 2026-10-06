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

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of method. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author shunji suzuki
 */
public class Step04MethodTest extends PlainTestCase {

    // ===================================================================================
    //                                                                         Method Call
    //                                                                         ===========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_method_call_basic() {
        String sea = supplySomething(); // overをもらう
        log(sea); // your answer? => over | o
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_call_many() {
        String sea = functionSomething("mystic"); // "tic"を"mys"に置換 -> "mysmys" (sea自体はこの後書き換わらない)
        consumeSomething(supplySomething()); // "over"を受け取り"mystic"に置換してログするだけ
        runnableSomething(); // seaとは無関係のローカル処理 (outofshadowを出すだけ)
        log(sea); // your answer? => mysmys | o
    }

    private String functionSomething(String name) {
        String replaced = name.replace("tic", "mys"); // mysmys | インスタンス生成
        log("in function: {}", replaced);
        return replaced;
    }

    private String supplySomething() {
        String sea = "over";
        log("in supply: {}", sea); // カンマ区切りってどうなるんだ？ブレースの中にseaが入るのか？だとしたら"in supply: over"か | o
        return sea; // 返すもの自体は over 
    }

    private void consumeSomething(String sea) {
        log("in consume: {}", sea.replace("over", "mystic"));
    }

    private void runnableSomething() {
        String sea = "outofshadow";
        log("in runnable: {}", sea);
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_object() {
        St4MutableStage mutable = new St4MutableStage();
        int sea = 904;
        boolean land = false;
        helloMutable(sea - 4, land, mutable); // 戻り値は受け取っていないので捨てられる
        if (!land) {
            sea = sea + mutable.getStageName().length(); // 904 + 6 (mystic)
        }
        log(sea); // your answer? => 910 | o
    }
    // Mutableなオブジェクトだから、Immutableと違って中身を塗り替える。

    private int helloMutable(int sea, Boolean land, St4MutableStage piari) {
        sea++; // 引数のseaは900のコピーなので、ローカル変数で901
        land = true; // ここもローカル
        piari.setStageName("mystic"); // 呼び出し元のmutableと同じインスタンスを触っている
        return sea;
    }

    private static class St4MutableStage {

        private String stageName;

        public String getStageName() {
            return stageName;
        }

        public void setStageName(String stageName) {
            this.stageName = stageName;
        }
    }

    // ===================================================================================
    //                                                                   Instance Variable
    //                                                                   =================
    private int inParkCount;
    private boolean hasAnnualPassport;

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_instanceVariable() {
        hasAnnualPassport = true;
        int sea = inParkCount; // intの初期値は0
        offAnnualPassport(hasAnnualPassport);
        for (int i = 0; i < 100; i++) {
            goToPark(); // hasAnnualPassportはtrueのままなので100回カウントされる
        }
        ++sea; // 1になるが、次の行で上書きされるので意味なし
        sea = inParkCount;
        log(sea); // your answer? => 100 | o
    }

    private void offAnnualPassport(boolean hasAnnualPassport) {
        hasAnnualPassport = false; // 引数のhasAnnualPassportを書き換えているだけ (インスタンス変数の中身を変えたいんだったらthis.hasAnnualPassportでやらないと)
    }

    private void goToPark() {
        // 引数ないからインスタンス変数が呼ばれる
        if (hasAnnualPassport) {
            ++inParkCount;
        }
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    // write instance variables here
    private boolean availableLogging = true;

    /**
     * Make private methods as followings, and comment out caller program in test method:
     * <pre>
     * o replaceAwithB(): has one argument as String, returns argument replaced "A" with "B" as String 
     * o replaceCwithB(): has one argument as String, returns argument replaced "C" with "B" as String 
     * o quote(): has two arguments as String, returns first argument quoted by second argument (quotation) 
     * o isAvailableLogging(): no argument, returns private instance variable "availableLogging" initialized as true (also make it separately)  
     * o showSea(): has one argument as String argument, no return, show argument by log()
     * </pre>
     * (privateメソッドを以下のように定義して、テストメソッド内の呼び出しプログラムをコメントアウトしましょう):
     * <pre>
     * o replaceAwithB(): 一つのString引数、引数の "A" を "B" に置き換えたStringを戻す 
     * o replaceCwithB(): 一つのString引数、引数の "C" を "B" に置き換えたStringを戻す 
     * o quote(): 二つのString引数、第一引数を第二引数(引用符)で囲ったものを戻す 
     * o isAvailableLogging(): 引数なし、privateのインスタンス変数 "availableLogging" (初期値:true) を戻す (それも別途作る)  
     * o showSea(): 一つのString引数、戻り値なし、引数をlog()で表示する
     * </pre>
     */
    public void test_method_making() {
        // use after making these methods
        String replaced = replaceCwithB(replaceAwithB("ABC")); // ABC -> BBC -> BBB
        String sea = quote(replaced, "'");
        if (isAvailableLogging()) {
            showSea(sea); // 'BBB'
        }
    }

    // #1on1: いいね、メソッドの定義位置が呼び出し順序と一致していて直感的で把握しやすい印象 (2026/10/06)
    // なんだかんだ人間、そういった付与情報に頼って頭の整理をしていたりする。
    // $上か下かは気にしてないけど、処理の流れやまとまりは意識して近くに置く。
    // 正解があるわけじゃないけど、何かしらの配慮があると読み手は嬉しい。
    // 「処理の順序」と「まとまり」ここがバッティングすることもある。
    // jfluteの場合、まとまりの存在感がどのくらいか？
    // 「処理の順序」と「まとまり」のハイブリッド。
    // 厳密には、「まとまり」を優先して、その後「処理の順序」を意識する。
    // LastaFluteのActionRequestProcessorの例。
    //
    // // 別にパソコンがなくてもプログラミングはできるよ
    // https://jflute.hatenadiary.jp/entry/20170923/nopcpg
    //
    // ↑の焼き付けやすいコードを意識することで、作業効率よくする。
    //
    // コード体裁デザインというのに意識を持ってもらいたい。
    // 良いコードはAIによって引き継がれる。「コード体裁デザイン」

    // write methods here
    // https://docs.oracle.com/javase/jp/8/docs/api/java/lang/String.html#replace-char-char-

    private String replaceAwithB(String target) {
        return target.replace("A", "B");
    }

    private String replaceCwithB(String target) {
        return target.replace("C", "B");
    }

    // #1on1: いいね、第二引数名がわかりやすい (2026/10/06)
    // 普通のローカル変数よりも、引数名は大事。
    // 引数名は、メソッドのインターフェース。呼び出す側が意識する変数。
    // 名前をしっかりつける費用対効果が高い。
    // せっかくなので、Stringのjavadocを見ながら標準APIの引数名の付け方を見てみた。
    private String quote(String target, String quotation) {
        return quotation + target + quotation;
    }

    private boolean isAvailableLogging() {
        return availableLogging;
    }

    private void showSea(String sea) {
        log(sea);
    }
}

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

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of variable. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author shunji suzuki
 */
public class Step01VariableTest extends PlainTestCase {

    // ===================================================================================
    //                                                                      Local Variable
    //                                                                      ==============
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_variable_basic() { // example, so begin from the next method
        String sea = "mystic";
        log(sea); // your answer? => mystic
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_initial() {
        String sea = "mystic";
        Integer land = 8;
        String piari = null;
        String dstore = "mai";
        sea = sea + land + piari + ":" + dstore;
        log(sea); // your answer? => mystic8null:mai
        //         javaって型が違っても足し算で連結できるんだろうか
        //         できるっぽい https://www.javadrive.jp/start/ope/index14.html
        //         じゃあ、nullはどうなるんだろう
        //         そもそも Stringはプリミティブ型じゃないからnullがありえるんだな
        //         java の型 https://qiita.com/t-yama-3/items/94ce9082b8c1eafda38f
        //         加算できそうじゃない？ https://qiita.com/sekitaka_1214/items/37f6ef9c52e21c8d4093
        //        言語仕様　https://docs.oracle.com/javase/jp/26/docs/specs/jls/index.html
        //        あった。　https://docs.oracle.com/javase/specs/jls/se21/html/jls-5.html#jls-5.1.11
        //        じゃあ全部連結されるか
        // done suzuki [いいね] しっかり調べてくださってありがとうございます by jflute (2026/07/22)
        // Javaだと、+してる中にStringがいたらどんな型もStringに化ていきます。
        // 内部的には、toString()メソッドが呼ばれて文字列に変換されていきます。
        // 今回だと、sea + land ... のところで、land.toString()が呼ばれるイメージです。 
        // #1on1: プログラミング言語の決め (C#だと空文字に) (2026/07/22)
        // nullって出ることのメリデメ:
        // デメリット: 意図しない文字列になっちゃう(by すずきさん) → 画面とかでnullって出ちゃう可能性高い
        //  (画面とメールの気合いの入れ方が違う。nullって出てきやすい)
        // メリット: 画面とかログとかでnullって出るので、開発時は不具合がわかりやすい
        // 些細な違いですけど、こういった細かいことでもメリデメを分析する習慣自体が大事。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_basic() {
        String sea = "mystic"; // 1
        String land = "oneman"; // 2
        sea = land;
        land = land + "'s dreams"; // 3(+1)
        log(sea); // your answer? => omeman
        //        上書きってことだよね
        //        log(land); -> omeman's dreams
        // done suzuki [ふぉろー] そうですね。sea変数の指し示すアドレスを上書きしています by jflute (2026/07/22)
        // seaとlandは一瞬、同じインスタンスを指し示します。
        // done jflute 1on1にて、変数とインスタンスの関係性について話する予定 (2026/07/22)
        // #1on1: インスタンスとは？ (2026/07/22)
        // クラスを実装化/実体化したもの。 by すずきさん
        // 一軒家のお話。インスタンスという概念に注目して欲しい。
        // プログラムをみたら、変数とインスタンスの関係性をイメージできるようになって欲しい。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_int() {
        int sea = 94;
        int land = 415;
        sea = land;
        land++;
        log(sea); // your answer? => 415
        log(land); // your answer? => 416
        // #1on1: プリミティブ型について。C言語やKotlinとの比較 (2026/07/22)
        // staticの話から、companion objectの話へ。
        // Singletonパターンとは？ KotlinとScalaの違いなどなど。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_BigDecimal() {
        BigDecimal sea = new BigDecimal(94); // 1
        BigDecimal land = new BigDecimal(415); // 2
        sea = land;
        sea = land.add(new BigDecimal(1)); // 3+4
        sea.add(new BigDecimal(1)); // 5+6
        log(sea); // your answer? => 417
        //        おっと予想外
        //        returns: this + augend ということは addは破壊的処理じゃないのか。
        //        pythonでいうところのlist.sort()かsorted()みたいなことか
        //        > BigDecimal は 高精度な10進数演算 をサポートするクラス https://efficientify.secret.jp/development/programming/%E3%80%90java%E4%B8%AD%E7%B4%9A%E3%80%91bigdecimal%E3%81%AE%E6%AD%A3%E3%81%97%E3%81%84%E4%BD%BF%E3%81%84%E6%96%B9%EF%BC%9A%E4%B8%B8%E3%82%81%E3%83%BB%E6%AF%94%E8%BC%83%E3%83%BB%E6%B3%A8%E6%84%8F/
        //      しかもBigDecimalはイミュータブルなクラスだから、add()は新しいBigDecimalを返すだけで、元のseaは変わらない
        // TODO suzuki 破壊的処理という言葉が良いですね笑 by jflute (2026/07/22)
        // そして、JavaDocをしっかり読まれているのGoodです。
        // immutableか？mutableか？でadd()も使い方が全然変わってきますので、
        // クラスを見かけたらimmutableか？mutableか？を真っ先に気にするでも良いくらいで。
        // TODO jflute 1on1にて、immutableのメリデメや歴史などのお話する予定 (2026/07/22)
        // #1on1: BigDecimalのソースコードリーディング、immutableかどうかの判別 (2026/07/22)
        // #1on1: immutable/mutableのメリデメ (2026/07/22)
        // immutableのメリット: 変えちゃいけないものを保持するのに適しているbyすすぎさん → 間違い防止
        // 間違い防止で可読性も良くなる、なぜ？ → 例外処理チェック処理が不要になる＆可読性...
        // 変わってないことが確定することで読み飛ばしやすくなる。
        // immutableのデメリット: 応用が利かない？byすずきさん コードが少しボリューム出る？byすずきさん
        //
        // TODO jflute ↑次回1on1で続き (2026/07/22)
    }

    // ===================================================================================
    //                                                                   Instance Variable
    //                                                                   =================
    private String instanceBroadway;
    private int instanceDockside;
    private Integer instanceHangar;
    private String instanceMagiclamp;

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_String() {
        String sea = instanceBroadway;
        log(sea); // your answer? => null
        //        参照型はNullが初期値になるらしい。Stringは参照型。プリミティブじゃない。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_int() {
        int sea = instanceDockside;
        log(sea); // your answer? => 0
        //        上と同じ
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_Integer() {
        Integer sea = instanceHangar;
        log(sea); // your answer? => null
        //        値型（プリミティブ型）、参照型（クラス型）、ラッパークラスに分けられるっぽい。 https://www.sejuku.net/blog/22828
        //        いや、Stringだけ浮いてるの気持ち悪いな。
        //        https://docs.oracle.com/javase/specs/jls/se8/html/jls-4.html これを読み解けたら型について網羅的にわかりそう。PrimitiveとReferenceしかないっぽい。
        // TODO suzuki [いいね] そう、Stringにはprimitiveがないですからね by jflute (2026/07/22)
        // あえていうなら、char[] がStringのprimitiveとも言えますが、
        // Integer/int みたいな単一値の関係性ではないので、やはりちょっと異質ですよね。
        // (Stringは実は複数の値を扱っている配列の抽象概念と言えるのかも!?)
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_via_method() {
        instanceBroadway = "bbb";
        instanceMagiclamp = "magician";
        helpInstanceVariableViaMethod(instanceMagiclamp);
        String sea = instanceBroadway + "|" + instanceDockside + "|" + instanceHangar + "|" + instanceMagiclamp;
        log(sea); // your answer? => bbb|1|null|burn
        //  x
        // instanceBroadway
        //     helpInstanceVariableViaMethod() で呼ばれていて"bigland"に変わっている。
        //     this.がついていなかったのでインスタンス変数を参照しないと思っていたが、同名のローカル変数や引数がなければ参照するらしい。
        //  instanceDockside
        //     helpInstanceVariableViaMethod() で前置インクリメントで1増えている。デフォは0
        //  instanceHangar
        //     helpInstanceVariableViaMethod() ではノータッチ。デフォのnullが文字列として連結される。
        // instanceMagiclamp
        //     helpInstanceVariableViaMethod() で呼ばれているが、引数と同名のため、引数の方が優先されている。インスタンス変数は参照されない。

        // 一旦自分が知っているクラスに存在する概念とJavaの言語仕様を整理する
        //    private String instanceBroadway;
        //    private int instanceDockside;
        //    private Integer instanceHangar;
        //    private String instanceMagiclamp;
        //    クラス直下に書かれている上記の変数たちは「インスタンス変数」
        //    「メンバ変数」とも呼ばれるが、よくよく考えたら何で？ -> C言語から来ているっぽい。 struct memberが由来らしい
        //
        //    private static int xxx; とかになってたら「クラス変数」
        //            なぜstaticというキーワードなのか -> クラスが読み込まれた瞬間にメモリの決まった場所に固定して配置されるから
        //
        //    public void myMethod() {
        //        int zzz = 0;
        //    }
        //    のようにメソッド内に書かれている変数は「ローカル変数」
        //        寿命はメソッドの実行が終わるまで
        //
        //　   今回の問題に関連して調べたこと
        //        1. インスタンス変数と同じ名前の仮引数をメソッドに渡すと、インスタンス変数の判定になるのか -> ならない
        //        優先順位は「ローカル側（引数・ローカル変数） ＞ インスタンス変数」
        //        引数とローカル変数に同じ名前をつけるとコンパイルエラーになる
        //        明示的にインスタンス変数を参照する場合は this. をつける
        //
        //        2. Javaでは全ての引数は値渡し（pass by value）である。参照型の引数も値渡しである。
        //　      プリミティブ型を渡した場合 -> 元の変数の値は変わらない
        //　      参照型を渡した場合 -> 受け取ったメソッド側で新しくアドレスを保持する変数が定義されるため、元の参照先は変わらない
        //        参照先がミュータブルなら変更できる
        //        Java.langパッケージに用意されている基本8つのラッパークラスはimmutable
        //            Byte, Short, Integer, Long, Float, Double, Character, Boolean

    }

    private void helpInstanceVariableViaMethod(String instanceMagiclamp) {
        instanceBroadway = "bigband";
        ++instanceDockside;
        instanceMagiclamp = "burn";
    }

    // ===================================================================================
    //                                                                     Method Argument
    //                                                                     ===============
    // -----------------------------------------------------
    //                                 Immutable Method-call
    //                                 ---------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_immutable_methodcall() {
        String sea = "harbor"; // 1
        int land = 415; // 2
        helpMethodArgumentImmutableMethodcall(sea, land);
        log(sea); // your answer? => harbor
    }

    private void helpMethodArgumentImmutableMethodcall(String sea, int land) { // 同名だけどローカル優先。sea, landの変数領域確保
        ++land;
        String landStr = String.valueOf(land); // is "416" 3
        sea.concat(landStr); // 多分 "harbor416" だけど、それはこのメソッド内でのお話
        // Stringはimmutableだからここでもインスタンス生成 4
    }
    // これは一発でいけた。インスタンス生成回数は4

    // -----------------------------------------------------
    //                                   Mutable Method-call
    //                                   -------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_mutable_methodcall() {
        StringBuilder sea = new StringBuilder("harbor");
        int land = 415;
        helpMethodArgumentMethodcall(sea, land);
        log(sea); // your answer? => 
    }

    private void helpMethodArgumentMethodcall(StringBuilder sea, int land) {
        ++land;
        sea.append(land);
    }

    // -----------------------------------------------------
    //                                   Variable Assignment
    //                                   -------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_variable_assignment() {
        StringBuilder sea = new StringBuilder("harbor");
        int land = 415;
        helpMethodArgumentVariable(sea, land);
        log(sea); // your answer? => 
    }

    private void helpMethodArgumentVariable(StringBuilder sea, int land) {
        ++land;
        String seaStr = sea.toString(); // is "harbor"
        sea = new StringBuilder(seaStr).append(land);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Define variables as followings:
     * <pre>
     * o local variable named sea typed String, initial value is "mystic"
     * o local variable named land typed Integer, initial value is null
     * o instance variable named piari typed int, without initial value
     * o show all variables by log() as comma-separated
     * </pre>
     * (変数を以下のように定義しましょう):
     * <pre>
     * o ローカル変数、名前はsea, 型はString, 初期値は "mystic"
     * o ローカル変数、名前はland, 型はInteger, 初期値は null
     * o インスタンス変数、名前はpiari, 型はint, 初期値なし
     * o すべての変数をlog()でカンマ区切りの文字列で表示
     * </pre>
     */
    public void test_variable_writing() {
        // define variables here
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Make your original exercise as question style about variable. <br>
     * (変数についてあなたのオリジナルの質問形式のエクササイズを作ってみましょう)
     * <pre>
     * _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
     * your question here (ここにあなたの質問を):
     *
     * _/_/_/_/_/_/_/_/_/_/
     * </pre>
     */
    public void test_variable_yourExercise() {
        // write your code here
    }
}

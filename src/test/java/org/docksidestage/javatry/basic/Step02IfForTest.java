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

import java.util.ArrayList;
import java.util.List;

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of if-for. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author shunji suzuki
 */
public class Step02IfForTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        if Statement
    //                                                                        ============
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_if_basic() { // example, so begin from the next method
        int sea = 904;
        if (sea >= 904) {
            sea = 2001;
        }
        log(sea); // your answer? => 2001
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_else_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else {
            sea = 7;
        }
        log(sea); // your answer? => 7 | o
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else if (sea >= 904) {
            sea = 7;
        } else if (sea >= 903) {
            sea = 8;
        } else {
            sea = 9;
        }
        log(sea); // your answer? => 7 | o
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_nested() {
        boolean land = false;
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
            sea = sea++ * 2;
        } else if (land && sea >= 904) {
            sea = 7;
            sea = ++sea * 2;
        } else if (sea >= 903 || land) {
            if (sea % 2 == 0) {
                sea = sea++ * 2; // 後置インクリメントの式が返す値を905と勘違いした
                log(sea);
                sea = sea++;
                log(sea);
            }
            if (!land) {
                land = true; // ここを通ればseaは10だ
            } else if (sea <= 903) {
                sea++;
            }
            if (sea < 1810) {
                sea = 8;
            }
        } else if (sea == 8) {
            sea++;
            land = false;
        } else {
            sea = 9;
        }
        // 1810
        // #1on1: ↑1810は間違いは自分で発見、実際は8だった (2026/08/17)
        // done suzuki なんで1810じゃなく8だったのか？探ってみてください by jflute (2026/08/17)
        // インクリメントの誤解（後置インクリメントと前置インクリメントの動作を切り分けられていなかった
        // if (x++ < 10) <- x < 10が評価された後にxが1増えるみたいな考え方をしていた
        // おそらく本質は「評価」と「値」の順序
        // 前置なら評価→値、後置なら値→評価
        // https://docs.oracle.com/javase/specs/jls/se7/html/jls-15.html#jls-15.14.2
        // > The value of the postfix increment expression is the value of the variable before the new value is stored.
        // #1on1: Good, じっくり文法的なところまで調べてくださってOK (2026/09/02)
        // 実際は、線引き自体を覚えておくのが面倒なので、単独行で使うとかが一般的かなと。
        if (sea >= 9 || (sea > 7 && sea < 9)) {
            sea--;
            if (sea % 2 == 1) {
                sea++;
            }
        }
        if (land) {
            sea = 10;
        }
        log(sea); // your answer? => 10 | o (retry)

        // #1on1: 漠然読み (2026/08/17)
        // o まず漠然読みで構造を把握する (この場合だと5分割)
        // o 見つけた当たり(ギャンブルポイント)でフォーカス読みと逆さ読み
        //
        // 構造を把握すると、わりと自然と当たりが見つかりやすい。(全体像をわかっていれば)
        // 一方で、ギャンブルに負けることもある。
        // でも損はない。構造を把握してるし、landのライフサイクルも把握してるので...
        // 0の状態から上から読むのに比べて、速く読めるようになっているはず。
        //
        // 一方で一方で、フォーカス読みをしたことで...
        // また次の当たり(ギャンブルポイント)が見つかることも多い。
        // そうしたら、またフォーカス読みしていけばいい。
        // それで何回かチャレンジして、3,4回繰り返しても、網羅的に読むよりは早い(ことが多い)。
        //
        // done suzuki [読み物課題] My Favorite Book: 仮説思考 by jflute (2026/08/17)
        // https://jflute.hatenadiary.jp/entry/20150111/kasetsu
        // 仮説を立てて実行することにより効率的に物事を進めることができると伝える著書だと認識していた
        // 仮説にぶつかってから、事実と仮説を切り分けていることが検証に効いてくる。
        // #1on1: ↑事実と仮説を切り分けとても大事Good (2026/09/02)
        // 自分の中でデマを起こさないように
        // // 自分の中でデマを広げさせない
        // https://jflute.hatenadiary.jp/entry/20110619/nodema
        // ↑1on1でちょこっと読み合わせ
        // その勘違いのメカニズムを知っておくことで多少防げるかもしれない。

        // #1on1: 新卒を取る大切さ (2026/08/17)
        // 組織的な話。
    }

    // ===================================================================================
    //                                                                       for Statement
    //                                                                       =============
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_inti_basic() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (int i = 0; i < stageList.size(); i++) {
            String stage = stageList.get(i);
            if (i == 1) {
                sea = stage;
            }
        }
        log(sea); // your answer? => dockside | o
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_basic() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (String stage : stageList) { // 拡張for文 https://qiita.com/masa-kunikata/items/72703085dbf59cc83052
            sea = stage; // 毎回置き換わる
        }
        log(sea); // your answer? => magiclamp | o
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_continueBreak() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (String stage : stageList) {
            if (stage.startsWith("br")) {
                continue;
            }
            sea = stage; // hangarまでは通る
            if (stage.contains("ga")) {
                break;
            }
        }
        log(sea); // your answer? => hangar | o

        // #1on1: Javaの文法のループ二つ (2026/09/02)
        // o intあいのfor文       : Java当初から (1995年)  // 伝統的なループ
        //  → 仕組み: ループごとにindexで探すという形になっている
        //  → ArrayListだったら速い(内部で配列の添字でアクセスしてるから)
        // o 拡張for文(foreach文) : Java10年目くらいから (2005年くらい)
        //  → 仕組み: 次の方どうぞ次の方どうぞ方式
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_listforeach_basic() {
        List<String> stageList = prepareStageList();
        StringBuilder sb = new StringBuilder();
        stageList.forEach(stage -> {
            if (sb.length() > 0) {
                return; // docksideが入ってreturn
            }
            if (stage.contains("i")) {
                sb.append(stage);
            }
        });
        String sea = sb.toString();
        log(sea); // your answer? => dockside | o

        // #1on1: forEach()メソッドとは？ (2026/09/02)
        // $Lambda式を入れるループ。
        // コンパイラーは、forEach()メソッドを、ループだと思ってない。
        // ただのメソッド呼び出しで、引数でLambdaを入れただけ。
        // ループの代理人をしているだけ。
        //
        // Java20年目くらいから (2015年) // 拡張for文の登場から10年くらい
        // 後から入るってことは、当然便利だから入るってことでしょうと。
        // forEach()は何が便利なの？何が良いのか？
        //
        // $Lambda式って変数に格納できる？
        // yes, できる
        // step8の先取りをして、Lambda式の仕組みとコールバックという言葉の話。
        // forEach()メソッドは、1ループの処理を表現した別クラス別メソッドをnewして引数で入れてるだけ。
        //
        // forEach()メソッドの存在意義に関しては、
        // forEach()メソッドへの置き換えエクササイズをやってもらって考えてもらって、
        // 1on1にてフォロー予定。

        // #1on1: 現場のKotlinでforを見つけたら教えてください (2026/09/02)
        // なんで filter() とかの抽象度の高いメソッドを使ってないんだろうか？
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Make list containing "a" from list of prepareStageList() and show it as log by loop. (without Stream API) <br>
     * (prepareStageList()のリストから "a" が含まれているものだけのリストを作成して、それをループで回してログに表示しましょう。(Stream APIなしで))
     */
    public void test_iffor_making() {
        // write if-for here
        List<String> stageList = prepareStageList();
        List<String> resultList = new ArrayList<>();
        for (String stage : stageList) {
            if (stage.contains("a")) {
                resultList.add(stage);
            }
        }
        for (String stage : resultList) {
            log(stage);
        }
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Change foreach statement to List's forEach() (keep result after fix) <br>
     * (foreach文をforEach()メソッドへの置き換えてみましょう (修正前と修正後で実行結果が同じになるように))
     */
    public void test_iffor_refactor_foreach_to_forEach() {
        // #1on1: そもそもforEach()メソッドの存在意義をエクササイズやりながら考えてみてください (2026/09/02)
        List<String> stageList = prepareStageList();
        String sea = null;
        for (String stage : stageList) {
            if (stage.startsWith("br")) {
                continue;
            }
            sea = stage;
            if (stage.contains("ga")) {
                break;
            }
        }
        log(sea); // should be same as before-fix
    }

    /**
     * Make your original exercise as question style about if-for statement. <br>
     * (if文for文についてあなたのオリジナルの質問形式のエクササイズを作ってみましょう)
     * <pre>
     * _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
     * your question here (ここにあなたの質問を):
     * 
     * _/_/_/_/_/_/_/_/_/_/
     * </pre>
     */
    public void test_iffor_yourExercise() {
        // write your code here
    }

    // ===================================================================================
    //                                                                        Small Helper
    //                                                                        ============
    private List<String> prepareStageList() {
        List<String> stageList = new ArrayList<>();
        stageList.add("broadway");
        stageList.add("dockside");
        stageList.add("hangar");
        stageList.add("magiclamp");
        return stageList;
    }
}

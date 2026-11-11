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
package org.docksidestage.bizfw.basic.buyticket;

// TODO suzuki javatryルールとして何か修正したらauthor追加をお願いします by jflute (2026/11/11)
/**
 * @author jflute
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_QUANTITY = 10;
    private static final int ONE_DAY_PRICE = 7400; // when 2019/06/15
    private static final int TWO_DAY_PRICE = 13200;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private int quantity = MAX_QUANTITY;
    private Integer salesProceeds; // null allowed: until first purchase

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========
    // you can rewrite comments for your own language by jflute
    // e.g. Japanese
    // /**
    // * 1Dayパスポートを買う、パークゲスト用のメソッド。
    // * @param handedMoney パークゲストから手渡しされたお金(金額) (NotNull, NotMinus)
    // * @throws TicketSoldOutException ブース内のチケットが売り切れだったら
    // * @throws TicketShortMoneyException 買うのに金額が足りなかったら
    // */
    /**
     * Buy one-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @return The ticket of one-day passport. (NotNull)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     */
    public Ticket buyOneDayPassport(Integer handedMoney) {
        doBuyPassport(ONE_DAY_PRICE, handedMoney);
        // チケットの種類はOneDayとTwoDayで違うので、チケットはそれぞれのメソッドで作って返す
        // (お釣りの計算は共通処理で)
        return new Ticket(ONE_DAY_PRICE);
    }

    // #1on1: いいね、JavaDoc綺麗にありがとうございます (2026/11/11)
    // publicメソッドなので、案内という感じで入れておきたい。
    // いいね、具体値を提示していることで、result of purchaseの概念理解につながっている。
    // いいね、(NotNull) ありがとうございます。
    // Javaとか値の有無を文法として表現できない言語だと、コメントの(NotNull)がめちゃ嬉しい。
    // LastaFluteとかLocalDateとかのJavaDocの例を見てみた。
    // TODO suzuki 断定列挙すると、ちょっと誤解を生む可能性もある。レシートが増えた時... by jflute (2026/11/11)
    // 断定列挙ではなく、"例えば列挙" にする。"e.g.", "など", "etc"
    // ここでやりたいのは、「resultの内訳網羅の伝達」ではなく、「概念理解の促進」である。
    /**
     * Buy two-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @return The result of purchase, which has the ticket of two-day passport and the change. (NotNull)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     */
    public TicketBuyResult buyTwoDayPassport(Integer handedMoney) {
        int change = doBuyPassport(TWO_DAY_PRICE, handedMoney);
        // チケットとお釣りの両方を返したいので、まとめて持つ TicketBuyResult
        return new TicketBuyResult(new Ticket(TWO_DAY_PRICE), change);
    }

    // OneDayとTwoDayで違うのは料金だけなので、料金を引数にして共通化
    // お釣りを返すのはTwoDayだけだが、共通処理では返しておく
    // #1on1: doBuyという名前、非常に良い！ (2026/11/11)
    // $自然とおもいついた → buyPassportが単なる共通メソッドなので外したかった
    // buyのままだと、メソッド一覧で紛れて見づらい、会話上区別がしづらい (些細だけど)
    // doBuyとprefixを変えて区別するやり方、わりと一般的に行われる小テクニック。
    // e.g. doBuyPassport(), internalBuyPassport()
    // TODO suzuki 戻り値がお釣りであることをどこかで示したい by jflute (2026/11/11)
    // o javadocで表現 (privateメソッドでも重要ロジックだったら書いても費用対効果高い)
    // o 計算の横にすらすらで handedMoney - price; // お釣り と書く
    // o 変数で表現する int change = handedMoney - price;
    // o メソッド表現する calculateChange()
    // $変数に関してはlintで言われるかもしれない
    // そうだったらもう別の方法で。
    private int doBuyPassport(int price, Integer handedMoney) {
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        // #1on1: 元々はこうだった (2026/10/19)
        // 現場でDBアプリであれば、トランザクションがあるので、若干こういうことあっても大丈夫だったり。
        // とはいえ、バリデーションとかは一番最初にやって、ただしい値の状態で本処理に進む方が頭もスッキリ。
        // ただ、ビジネスロジックの中じゃないとチェックできないものもある。
        // e.g. DBを検索しないとチェックできないもの (時にバリデーションの中でDBアクセスすることもあるが)
        // e.g. それこそ残りチケットの更新処理で、0枚になる瞬間の時
        // 業務例外系で途中で例外をthrowすることわりとある。
        // その場合、それまでの更新した内容は元に戻さないといけない。(そのときはDBは便利)
        // 一方で、マイクロサービスアーキテクチャ構成とかで、リモートAPI呼び出しが多い場合、
        // トランザクションの恩恵が受けられないので、順序を間違えると元に戻すのを手動でやらない。
        // わかってるものに関しては、手動で戻そうと努力するが、今回みたいなケースで予期せぬパターンでバグることも。
        // 現場だと、この流れのバグが、非常にわかりにくいケースで登場することもある。
        //
        // 流れのバグ → 比較的、見つけにくい → ので意識して見ていかないと見つからない
        // (それぞれの処理の、前後関係だいじょうぶかな？って常に疑うくらいの意識で良い)
        //
        //--quantity;
        if (handedMoney < price) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        if (salesProceeds != null) { // second or more purchase
            salesProceeds = salesProceeds + price;
        } else { // first purchase
            salesProceeds = price;
        }
        --quantity;
        return handedMoney - price;
    }

    public static class TicketSoldOutException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketSoldOutException(String msg) {
            super(msg);
        }
    }

    public static class TicketShortMoneyException extends RuntimeException {
        // https://docs.oracle.com/javase/jp/8/docs/api/java/lang/RuntimeException.html

        private static final long serialVersionUID = 1L;

        public TicketShortMoneyException(String msg) {
            super(msg);
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getQuantity() {
        return quantity;
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
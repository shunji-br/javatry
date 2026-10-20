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

/**
 * @author jflute
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_QUANTITY = 10;
    private static final int ONE_DAY_PRICE = 7400; // when 2019/06/15

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
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     */
    public void buyOneDayPassport(Integer handedMoney) {
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
        if (handedMoney < ONE_DAY_PRICE) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        if (salesProceeds != null) { // second or more purchase
            salesProceeds = salesProceeds + handedMoney;
        } else { // first purchase
            salesProceeds = handedMoney;
        }
        --quantity;
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

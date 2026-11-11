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
 * チケットを買った結果 (チケットとお釣り)
 * @author shunji suzuki
 */
public class TicketBuyResult {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // 買った後に変わるものではないので final
    private final Ticket ticket;
    private final int change;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBuyResult(Ticket ticket, int change) {
        // #1on1: 他のアンチパターンの紹介 (2026/11/11)
        // o 引数と定義の順序違い
        // o getChange()でお釣りの計算
        //  (getのコンセプトから来るもの)
        //  i getだと何度も呼ばれる可能性
        //  i getのコンセプト的にそこにロジックがあると思わないので見つけづらい
        //  (resultのコンセプトから来るもの)
        //  i 入れ物クラス感が強い
        //  i 仮にResultProvider, ResultCreator
        // 
        // new Ticket()も似た話。コンストラクターでやるにしてもコンセプトから一般的ではない。
        this.ticket = ticket;
        this.change = change;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public Ticket getTicket() {
        return ticket;
    }

    public int getChange() {
        return change;
    }
}
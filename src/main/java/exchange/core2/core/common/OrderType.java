/*
 * Copyright 2019 Maksim Zheravin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package exchange.core2.core.common;

import lombok.Getter;

/**
 * 订单类型(订单执行策略)
 */
@Getter
public enum OrderType {

    // Good till Cancel - equivalent to regular limit order
    /**
     * 一直有效至取消（GTC）：订单将持续有效直到完全执行或被交易者手动取消。
     * GTC适合愿意等待全部合约在指定的价格成交的交易者，并可以随时灵活地取消尚未成交的合约
     */
    GTC(0),

    // Immediate or Cancel - equivalent to strict-risk market order
    /**
     * 立即成交或取消（IOC）: 订单必须立即以限价或更佳的价格成交。
     * 如果订单无法立即完全成交，未成交的部分将被取消。IOC通常用于避免大额订单在偏离理想的价位成交，通过此设置，未能在指定价位成交的部分将被取消
     */
    IOC(1), // with price cap
    IOC_BUDGET(2), // with total amount cap

    // Fill or Kill - execute immediately completely or not at all
    /**
     * 完全成交或取消（FOK）：订单必须立即以委托价或更佳的价格全部成交，否则将被完全取消，不允许部分成交
     */
    FOK(3), // with price cap
    FOK_BUDGET(4); // total amount cap

    private final byte code;

    OrderType(final int code) {
        this.code = (byte) code;
    }

    public static OrderType of(final byte code) {
        switch (code) {
            case 0:
                return GTC;
            case 1:
                return IOC;
            case 2:
                return IOC_BUDGET;
            case 3:
                return FOK;
            case 4:
                return FOK_BUDGET;
            default:
                throw new IllegalArgumentException("unknown OrderType:" + code);
        }
    }

}

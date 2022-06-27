package com.anas.cashier.Interface;

import com.anas.cashier.RoomDB.Tables.DebitPeople;

public interface OnClickItemDebt {

    void onClickItem(DebitPeople debit);
    void OnClickItemDelete(DebitPeople people);
    void OnClickItemEdit(DebitPeople people);
}

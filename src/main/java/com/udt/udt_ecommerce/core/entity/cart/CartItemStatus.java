package com.udt.udt_ecommerce.core.entity.cart;

public enum CartItemStatus {
  ACTIVE, // Item is actively in the cart and ready for checkout.
  PAYMENT_PENDING, // Item is being waited to purchased
  PURCHASED, // Item is purchased
  STOPPED // Item is out of stock
}

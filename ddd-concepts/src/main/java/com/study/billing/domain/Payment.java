package com.study.billing.domain;

import com.study.shared.Money;

import java.util.UUID;

/**
 * 결제(Payment) 도메인 컨텍스트
 * - 결제 처리, 결제 수단 관리, 결제 기록 등을 담당합니다.
 * - 예: 결제 승인, 결제 취소, 환불 처리 등
 */
public record Payment(UUID invoiceId, Money amount, String method) {
}

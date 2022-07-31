package com.hanwha.tax.apiserver.repositorysecond;

import com.hanwha.tax.apiserver.entitysecond.MailReceiver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailReceiverRepository extends JpaRepository<MailReceiver, Long> {
}

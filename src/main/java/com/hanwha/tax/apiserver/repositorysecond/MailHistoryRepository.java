package com.hanwha.tax.apiserver.repositorysecond;

import com.hanwha.tax.apiserver.entitysecond.MailHistory;
import com.hanwha.tax.apiserver.entitysecond.MailReceiver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailHistoryRepository extends JpaRepository<MailHistory, Long> {
}

package org.zerock.myapp.domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@Log4j2
@ToString(exclude = "fkAdmins")

@Entity
@Table(name = "notice")
public class Notice {

    @Id
    @GeneratedValue(generator = "noticeNum")
    @SequenceGenerator(name = "noticeNum", initialValue = 1, allocationSize = 1)
    private Long num;

    @Column(nullable = false)


    @Transient
    private Long writer;


    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @CreationTimestamp
    private Date createDate;

    @Column(nullable = false)
    @UpdateTimestamp
    private Date updateDate;




    @ManyToOne(
            targetEntity = Admins.class,
            fetch = FetchType.EAGER,
            optional = false
    )
    @JoinColumn(name = "noticeWriter", referencedColumnName = "adminNum", nullable = false)
    private Admins fkAdmins;

    public void setAdmins(Admins fkAdmins){
        log.trace("setAdmins({}) Invoked.", fkAdmins);
        this.fkAdmins = fkAdmins;
        this.fkAdmins.getNoticeListAdmins().add(this);
    } // setAdmins




} // end class

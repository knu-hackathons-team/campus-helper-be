package com.soft.campushelper.member;

import com.soft.campushelper.global.constants.MessageConstants;
import com.soft.campushelper.global.entity.BaseTimeEntity;
import com.soft.campushelper.global.exception.InvalidConditionException;
import com.soft.campushelper.post.Post;
import com.soft.campushelper.work.Work;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String college;

    @Column(nullable = false)
    @PositiveOrZero
    private int point;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    @OneToMany(mappedBy = "worker", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Work> works;

    public void decreasePoint(int point){
        if(this.point - point < 0){
            throw new InvalidConditionException(MessageConstants.INSUFFICIENT_POINTS);
        }
        this.point -= point;
    }

    public void increasePoint(int point){
        this.point += point;
    }
}

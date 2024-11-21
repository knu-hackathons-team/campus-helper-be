package com.soft.campushelper.Member;

import com.soft.campushelper.global.entity.BaseTimeEntity;
import com.soft.campushelper.global.exception.InvalidConditionException;
import com.soft.campushelper.post.Post;
import jakarta.persistence.*;
import lombok.*;

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

    private String loginId;

    private String password;

    private String nickname;

    private String college;

    private int point;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "writer")
    private List<Post> posts;

    public void decreasePoint(int point){
        if(this.point - point < 0){
            throw new InvalidConditionException("포인트가 부족합니다.");
        }
        this.point -= point;
    }
}

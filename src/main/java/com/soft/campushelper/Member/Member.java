package com.soft.campushelper.Member;

import com.soft.campushelper.global.entity.BaseTimeEntity;
import com.soft.campushelper.post.Post;
import jakarta.persistence.*;
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

    private String loginId;

    private String password;

    private String nickname;

    private String college;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "writer")
    private List<Post> posts = new ArrayList<>();
}

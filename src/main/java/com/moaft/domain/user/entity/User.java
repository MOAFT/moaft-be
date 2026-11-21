package com.moaft.domain.user.entity;

import com.moaft.domain.record.entity.MediaAsset;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users",
uniqueConstraints = {
        @UniqueConstraint(name = "UK_users_username", columnNames = "username"),
        @UniqueConstraint(name = "UK_users_social", columnNames = {"social_provider", "oauth_id"})})

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String username;

    @Column(nullable = false, length = 10)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20, columnDefinition = "VARCHAR(20)")
    private SocialProvider socialProvider;

    @Column(nullable = false, length = 255)
    private String oauthId;

    @Column(length = 255)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_image_asset_id")
    private MediaAsset profileImage;

    @Column(length = 150)
    private String bio;

    @Column(nullable = false, columnDefinition = "TINTINT(1)")
    private Boolean isProfilePublic = true;
}

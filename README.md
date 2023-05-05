# VERBY - 커버 영상 공유 플랫폼

Verby는 <mark>Verse by</mark>의 약자로 커버 곡 영상을 공유하는 플랫폼입니다.

> API: http://api.verby.co.kr/docs/api-docs.html

--- 

## 팀 소개

### 기획

<table>
    <tr>
        <td>
            <a>
                <img src="https://user-images.githubusercontent.com/63458653/190153215-1543dc7e-c5b1-46be-a4e7-1da3ac90bf44.png" width="100px" />
            </a>
        </td>
        <td>
            <a>
                <img src="https://user-images.githubusercontent.com/63458653/190397962-a03a98e9-e245-405e-81e4-d6c241500d40.jpeg" width="100px" />
            </a>
        </td>
    </tr>
    <tr>
        <td>
            <b>박완</b>
        </td>
        <td>
            <b>정희라</b>
        </td>
    </tr>
    <tr>
        <td>
            <b>Product Owner</b>
        </td>
        <td>
            <b>Product Manager</b>
        </td>
    </tr>
</table>

### 디자인

<table>
    <tr>
        <td>
            <a>
            </a>
                <img src="https://user-images.githubusercontent.com/63458653/191248454-4204d874-d529-46c6-bad3-18a6f4857f0f.png" width="100px" />
        </td>
        <td>
            <a>
                <img src="https://user-images.githubusercontent.com/63458653/191250197-3b13ac38-5a44-4b9f-b7a5-3791c7d0fc40.png" width="100px" />
            </a>
        </td>
        <td>
            <a>
                <img src="https://user-images.githubusercontent.com/63458653/191249294-cf077048-4808-4a19-b8e6-3ba6d586e57f.png" width="100px" />
            </a>
        </td>
    </tr>
    <tr>
        <td>
            <b>이윤지</b>
        </td>
        <td>
            <b>김주현</b>
        </td>
        <td>
            <b>한수민</b>
        </td>
    </tr>
    <tr>
        <td>
            <b>UI/UX Design</b>
        </td>
        <td>
            <b>UI/UX Design</b>
        </td>
        <td>
            <b>UI/UX Design</b>
        </td>
    </tr>
</table>


### 개발

<table>
    <tr>
        <td>
            <a href="https://github.com/Bigstar1108">
                <img src="https://avatars.githubusercontent.com/u/65744080?v=4" width="100px" />
            </a>
        </td>
        <td>
            <a href="https://github.com/violetbeach">
                <img src="https://avatars.githubusercontent.com/u/63458653?s=400&u=64e83bec8c038ee120e3495ada18a6b4e77b1359&v=4" width="100px" />
            </a>
        </td>
    </tr>
    <tr>
        <td>
            <b>황대성</b>
        </td>
        <td>
            <b>이재헌</b>
        </td>
    </tr>
    <tr>
        <td>
            <b>Front-End</b>
        </td>
        <td>
            <b>Back-End</b>
        </td>
    </tr>
</table>

[Verby-Github-Repository](https://github.com/verby-korea)

## 프로젝트 소개

### 프로젝트 기간

- 기획 및 설계 : 22.06.08 ~
- 프로젝트 구현 : 22.07.27 ~
- 버그 수정 및 리팩토링 :

## 기술 스택

<div align=left>
<img src="https://img.shields.io/badge/java 17-007396?style=for-the-badge&logo=java&logoColor=white">
<img src="https://img.shields.io/badge/springboot 2.7.1-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
<img src="https://img.shields.io/badge/spring security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white">
<img src="https://img.shields.io/badge/spring data jpa-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
<img src="https://img.shields.io/badge/gradle 7.5-02303A?style=for-the-badge&logo=gradle&logoColor=white">
<img src="https://img.shields.io/badge/junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white">

<br>

<img src="https://img.shields.io/badge/mariadb 10.6.8-4479A1?style=for-the-badge&logo=MariaDB&logoColor=white">
<img src="https://img.shields.io/badge/RestDocs-8CA1AF?style=for-the-badge&logo=readthedocs&logoColor=white">

<br>

<img src="https://img.shields.io/badge/amazon aws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white">
<img src="https://img.shields.io/badge/Circle CI-2088FF?style=for-the-badge&logo=CircleCI&logoColor=white">
<img src="https://img.shields.io/badge/sonarqube-4E9BCD?style=for-the-badge&logo=sonarqube&logoColor=white">
<img src="https://img.shields.io/badge/codecov-F01F7A?style=for-the-badge&logo=Codecov&logoColor=white">
<br>

<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
<img src="https://img.shields.io/badge/slack-4A154B?style=for-the-badge&logo=slack&logoColor=white">
<img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white">
</div>

## ERD

<img src="./docs/erd.png" style="border:1px solid; border-radius: 12px;"/>

> Link: https://www.erdcloud.com/d/uCXnKBd5X6DAZmjhD
 
## 패키지 구조

패키지 구조는 DDD(Domain-Driven-Design)의 바운디드 컨텍스트를 기반으로 합니다.

```
com.verby.core
    +- cover
         +- command
             +- application
             |   +- CoverService.java
             |   +- CoverHitService.java
             |   +- PostCoverRequest.java
             |   +- PostedCoverInfo.java
             |   +- CoverStorageService.java
             |   +- CoverLikeService.java
             |   +- ...
             +- domain
             |   +- Cover.java
             |   +- CoverHit.java
             |   +- CoverLike.java
             |   +- CoverRepository.java
             |   +- ContestService.java
             |   +- ...
         +- query
             +- application
             |   +- CoverSummaryQueryService.java
             |   +- CoverSearchRequest.java
             |   +- CoverDetailService.java
             |   +- ...
             +- domain
             |   +- CoverQueryModel.java
             |   +- CoverQueryModelRepository.java
             |   +- ...
         +- infrastructure
         |   +- CoverQueryModelRepositoryImpl.java
         |   +- CoverServiceImpl.java
         |   +- ContestServiceImpl.java
         |   +- SongServiceImpl.java
         |   +- ...
         +- exception
         |   +- CoverExistsAlreadyException.java
      +- artist
      +- song
      +- contest
      +- ...
```

## System Architecture

### API Server

<img src="./docs/project-architecture/api-server.png" style="border: 1px solid black;border-radius: 12px;" width="100%"/>

### Batch Server

<img src="./docs/project-architecture/batch-server.png" style="border:1px solid black; border-radius: 12px;" width="50%%"/>

### Consumer Server

<img src="./docs/project-architecture/consumer-server.png" style="border:1px solid black; border-radius: 12px;" width="50%"/>

## CI/CD

<img src="./docs/cicd.png" style="border: 1px solid black;border-radius: 12px;" width="100%"/>

## 기타 문서

> 팀 전체 노션: https://bevel-buzzard-64c.notion.site/verby-3d934aa2c3044000b61361e443166d90
> 
> 백엔드 노션: https://bevel-buzzard-64c.notion.site/67bc72df37c94228a2cd6b5df488f2e3
> 
> Trouble Shooting: https://bevel-buzzard-64c.notion.site/Trouble-Shooting-003614a4bd214a92886b690c548a1eaf

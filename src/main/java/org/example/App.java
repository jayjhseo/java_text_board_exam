package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    Scanner sc = new Scanner(System.in);
    long lastId = 0;
    List<Article> articles = new ArrayList<>();
    List<Member> members = new ArrayList<>();
    Member loginedMember = null;

    public void run() {
        System.out.println("== 텍스트 앱 시작 ==");

        while (true) {
            System.out.print("명령) ");
            String command = sc.nextLine();

            if (command.equals("등록")) {
                lastId++;
                System.out.print("제목: ");
                String title = sc.nextLine();
                System.out.print("내용: ");
                String content = sc.nextLine();
                System.out.printf("%d번 텍스트가 등록되었습니다.\n", lastId);
                Article article = new Article(lastId, title, content);
                articles.add(article);
            } else if (command.equals("종료")) {
                System.out.println("프로그램이 종료됩니다.");
                break;
            } else if (command.equals("목록")) {
                System.out.println("번호 / 제목 / 내용");
                for (int i = 0; i < articles.size(); i++) {
                    Article article = articles.get(i);
                    System.out.printf("%d, %s, %s\n", article.getId(), article.getTitle(), article.getContent());
                }
            } else if (command.equals("삭제")) {
                System.out.println("삭제할 텍스트의 ID값을 입력해주세요");
                long removeId = Long.parseLong(sc.nextLine());
                boolean checkId = false;
                for (int i = 0; i < articles.size(); i++) {
                    Article article = articles.get(i);
                    if (article.getId() == removeId) {
                        articles.remove(article);
                        checkId = true;
                    }
                }
                if (checkId == false) {
                    System.out.println("해당 ID값에 텍스트가 존재하지 않습니다.");
                    continue;
                }
                System.out.printf("%s번의 텍스트가 삭제되었습니다.\n", removeId);
            } else if (command.equals("수정")) {
                System.out.println("수정할 텍스트의 ID값을 입력해주세요");
                long modifyId = Long.parseLong(sc.nextLine());
                boolean checkId = false;
                for (int i = 0; i < articles.size(); i++) {
                    Article article = articles.get(i);
                    if (article.getId() == modifyId) {
                        checkId = true;
                        System.out.print("수정 제목: ");
                        String title = sc.nextLine();
                        System.out.print("수정 내용: ");
                        String content = sc.nextLine();
                        article.setTitle(title);
                        article.setContent(content);
                    }
                } if (checkId == false) {
                    System.out.println("해당 ID값에 텍스트가 존재하지 않습니다.");
                    continue;
                }
                System.out.printf("%s번의 텍스트가 수정되었습니다.\n", modifyId);
            } else if (command.equals("회원가입")) {
                String userId;
                String password;
                String passwordConfirm;
                while (true) {
                    System.out.print("아이디: ");
                    userId = sc.nextLine();
                    boolean checkUserId = false;
                    for (int i = 0; i < members.size(); i++) {
                        Member member = members.get(i);
                        if (member.getUserId().equals(userId)) {
                            checkUserId = true;
                        }
                    } if (checkUserId) {
                        System.out.println("해당 아이디가 이미 존재합니다.");
                        continue;
                    } break;
                }
                while (true) {
                    System.out.print("비밀번호: ");
                    password = sc.nextLine();
                    System.out.print("비밀번호 확인: ");
                    passwordConfirm = sc.nextLine();

                    if (password.equals(passwordConfirm) == false) {
                        System.out.println("비밀번호가 일치하지 않습니다.");
                        continue;
                    }
                    System.out.println("회원가입이 완료되었습니다.");
                    break;
                }
                Member member = new Member(userId, password);
                members.add(member);
            } else if(command.equals("로그인")) {
                if (loginedMember != null) {
                    System.out.println("이미 로그인을 한 상태입니다.");
                    continue;
                }
                System.out.print("아이디: ");
                String userId = sc.nextLine();
                Member member = null;
                boolean checkLoginedId = false;
                for (int i = 0; i < members.size(); i++) {
                    member = members.get(i);
                    if (member.getUserId().equals(userId)) {
                        checkLoginedId = true;
                        break;
                    }
                } if (checkLoginedId == false) {
                    System.out.println("해당 아이디가 존재하지 않습니다.");
                    continue;
                }
                System.out.print("비밀번호: ");
                String password = sc.nextLine();
                if (member.getPassword().equals(password) == false) {
                    System.out.println("비밀번호가 일치하지 않습니다.");
                    continue;
                }
                loginedMember = member;
                System.out.printf("%s님 환영합니다. 로그인 성공.\n", userId);
            } else if(command.equals("로그아웃")) {
                if (loginedMember == null) {
                    System.out.println("현재 로그인한 상태가 아닙니다.");
                    continue;
                }
                System.out.println("로그아웃 하였습니다.");
                loginedMember = null;
            }
        }
    }
}

package com.uppercaseband.bootstrap;

import com.uppercaseband.domain.Article;
import com.uppercaseband.domain.Category;
import com.uppercaseband.domain.Media;
import com.uppercaseband.domain.MediaType;
import com.uppercaseband.repositories.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
@Slf4j
public class BootStrap implements CommandLineRunner {

    private final ArticleRepository articleRepository;


    @Override
    public void run(String... args) {
        loadArticles();

        articleRepository.count().subscribe(count -> log.debug("Article count is: {}", count));
    }


    private void loadArticles() {
        articleRepository.count().subscribe(count -> {
            if (count == 0) {

                Media article1Media = Media.builder()
                        .type(MediaType.IMAGE)
                        .path("/images/tanging_ikaw.png")
                        .build();

                Article article1 = Article.builder()
                        .title("Tanging Ikaw")
                        .description("The brand new single from UPPERCASE released under Radio Insect Records")
                        .displayOrder(100)
                        .category(Category.HIGHLIGHTS)
                        .subContent("<a href='https://open.spotify.com/artist/6h4pjpssOa3fBNiQmSkgOB?si=lbGJiYu7R_6ouDMIs7Jv3A'>CHECK IT OUT</a>")
                        .media(article1Media)
                        .build();


                Media article2Media = Media.builder()
                        .type(MediaType.IMAGE)
                        .path("/images/tsw_album.png")
                        .build();

                Article article2 = Article.builder()
                        .title("'Time Space Warp' Album Launch")
                        .description("May 17, 2013 – Hard Rock Café Toronto")
                        .displayOrder(200)
                        .category(Category.HIGHLIGHTS)
                        .subContent("<p><a href='https://www.facebook.com/pg/cyberpinoyradio/photos/?tab=album&album_id=657041557656169'>SEE EVENT PICS</a></p><p><a href='https://youtu.be/yNt0JV8or3k?list=PL0AgfLYM2K_sKTvDMqLY4sDr8Pi1zadB0'>WATCH EVENT VIDEO</a></p>")
                        .media(article2Media)
                        .build();


                Media article3Media = Media.builder()
                        .type(MediaType.VIDEO)
                        .path("<iframe width=\\\"560\\\" height=\\\"315\\\" src=\\\"//www.youtube.com/embed/ZfNUPtLtH5w\\\" frameborder=\\\"0\\\" allowfullscreen></iframe>")
                        .build();

                Article article3 = Article.builder()
                        .title("'Time Space Warp' Music Video Launch")
                        .description("July 12, 2013 – Prestige Bar, North York")
                        .displayOrder(300)
                        .category(Category.EVENTS)
                        .media(article3Media)
                        .build();


                articleRepository.save(article1).subscribe();
                articleRepository.save(article2).subscribe();
                articleRepository.save(article3).subscribe();
            }
        });
    }
}

import pygame
import random

pygame.init()

screen = pygame.display.set_mode((1280, 720))
clock = pygame.time.Clock()
running = True
pygame.display.set_caption("Aim Trainer")

hit_count = 0
miss_count = 0

myfont = pygame.font.SysFont("Comic Sans MS", 20)
text = myfont.render("Hit!", 1, "white")


screen_width = 1280
screen_height = 720

circle_size = 20
object_width = 15

x = random.randint(0, screen_width - object_width)
y = random.randint(0, screen_height - object_width)

show_text = False
hit_time = None


fade_start = None
fade_duration = 500
wait_for_next_target = False 


while running:

    score = myfont.render(f"Score: {hit_count}", 1, "white")
    score_rect = text.get_rect(center=(50, 50))

    misses = myfont.render(f"Misses: {miss_count}", 1, "white")
    miss_rect = text.get_rect(center=(50, 70))

    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False

        if event.type == pygame.MOUSEBUTTONUP:

            mouse_x, mouse_y = event.pos
            
            distance = (mouse_x - x) ** 2 + (mouse_y - y) ** 2

            if distance <= circle_size ** 2 and not wait_for_next_target:
                hit_count += 1
                hit_time = pygame.time.get_ticks()
                show_text = True

                fade_start = pygame.time.get_ticks()
                wait_for_next_target = True
                
            else:
                miss_count += 1 
    
    screen.fill("black")
    screen.blit(score, score_rect)
    screen.blit(misses, miss_rect)

    if show_text:
        time_since_hit = pygame.time.get_ticks() - hit_time

        if time_since_hit < 500:
            text_rect = text.get_rect(center=(x, y))
            screen.blit(text, text_rect)
        else:
            show_text = False
            hit_time = None

    if wait_for_next_target:
        elapsed = pygame.time.get_ticks() - fade_start

        if elapsed >= fade_duration:
            x = random.randint(0, screen_width - object_width)
            y = random.randint(0, screen_height - object_width)

            wait_for_next_target = False
            alpha = 255

        else:
            alpha = max(0, 255 - int((elapsed / fade_duration) * 255))

    else:
        alpha = 255

    circle_surface = pygame.Surface((circle_size * 2, circle_size * 2), pygame.SRCALPHA)    
    pygame.draw.circle(circle_surface, (255, 0, 0, alpha), (circle_size, circle_size), circle_size)
    screen.blit(circle_surface, (x -  circle_size, y - circle_size))

    pygame.display.flip()
    clock.tick(60)

pygame.quit()
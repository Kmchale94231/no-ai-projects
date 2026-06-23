import random
from pprint import pprint
import time, threading

suits = ['Hearts', 'Diamonds', 'Spades', 'Clubs'] 
values = ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'Jack', 'Queen', 'King', 'Ace']

cards = []

for suit in suits:
    for value in values:
        cards.append((suit, value))

random.shuffle(cards)

p1_cards = cards[0:26]
p2_cards = cards[26:52]

col_width = 20


def load_animation():

    art = ["⢿", "⣻", "⣽", "⣾", "⣷", "⣯", "⣟", "⡿"]
    stop_animation = threading.Event()

    def spinner():
        i = 0
        while not stop_animation.is_set():
            print(f"\rPress Enter to draw the first card: {art[i]}", end="", flush=True)
            time.sleep(0.5)
            i = (i + 1) % len(art)

    thread = threading.Thread(target=spinner)
    thread.start()

    input()

    stop_animation.set()
    thread.join()


def draw_card(user_1, user_2, p1_cards, p2_cards):
        
        if len(p1_cards) == 0:
            print(f"{user_2} wins!")
            return "game_over"
        
        elif len(p2_cards) == 0:
            print(f"{user_1} wins!")
            return "game_over"       

        else:   

            chosen_card1 = random.choice(p1_cards)
            display_card1 = chosen_card1[1] + " of " + chosen_card1[0]
            p1_cards.remove(chosen_card1)

            chosen_card2 = random.choice(p2_cards)
            display_card2 = chosen_card2[1] + " of " + chosen_card2[0]
            p2_cards.remove(chosen_card2)


            print(f"{user_1:^18}{user_2:^18}")
            print(f"{display_card1:^18}{display_card2:^18}\n")

            return chosen_card1, chosen_card2


def compare_cards(chosen_card1, chosen_card2):

    comp1 = chosen_card1[1] 
    comp2 = chosen_card2[1]

    position_1 = 0
    position_2 = 0

    for i in range(len(values)):
        if values[i] == comp1:
            position_1 = i
        if values[i] == comp2:
            position_2 = i

    if position_1 > position_2:
        return "player1"
    elif position_1 < position_2:
        return "player2"
    else:
        return "tie"


def award_cards(winning_pile, chosen_card1, chosen_card2):
    winning_pile.append(chosen_card1)
    winning_pile.append(chosen_card2)
    return winning_pile


def handle_war(user_1, user_2, p1_cards, p2_cards, chosen_card1, chosen_card2, war_pile):

        war_pile.append(chosen_card1)
        war_pile.append(chosen_card2)


        if len(p1_cards) < 4:
            print(f"{user_2} wins the game!")
            return "game_over"
        
        elif len(p2_cards) < 4:
            print(f"{user_1} wins the game!")
            return "game_over"
        
        else:

            for i in range(3):

                p1_drawn_card = random.choice(p1_cards)
                war_pile.append(p1_drawn_card)
                p1_cards.remove(p1_drawn_card)

                p2_drawn_card = random.choice(p2_cards)
                war_pile.append(p2_drawn_card)
                p2_cards.remove(p2_drawn_card)


            final_p1_card = random.choice(p1_cards)
            p1_cards.remove(final_p1_card)

            final_p2_card = random.choice(p2_cards)
            p2_cards.remove(final_p2_card)


        war_winner = compare_cards(final_p1_card, final_p2_card)

        if war_winner == "player1":
            war_pile.append(final_p1_card)
            war_pile.append(final_p2_card)
            p1_cards.extend(war_pile)
            return "player1"
        
        elif war_winner == "player2":
            war_pile.append(final_p1_card)
            war_pile.append(final_p2_card)
            p2_cards.extend(war_pile)
            return "player2"
        
        else:
            return handle_war(user_1, user_2, p1_cards, p2_cards, final_p1_card, final_p2_card, war_pile)



def main():

    print("------Welcome to a game of war!------")

    user_1 = input("Player 1 Enter Your Name: ")
    print(f"Welcome {user_1}!\n")

    user_2 = input("Player 2 Enter Your Name: ")
    print(f"Welcome {user_2}!\n\n\n")

    print("""How to Play:
        
    1. The card deck will be shuffled and evenly distributed between both players.
    2. Each player will receive 26 cards.
    3. Players keep their cards in a single stack, face-down, and must not look at them.
    4. Both players simultaneously flip their top card face-up and place them in the center of the table.
    5. The player with the higher-ranking card wins both cards and places them face down at the bottom of their stack.
    6. Play continues in rounds until one player successfully captures all the cards in the deck.
    """)

    input("Shall we being? Press Enter to continue...\n")

    load_animation()

    while len(p1_cards) > 0 and len(p2_cards) > 0:

        chosen_card1, chosen_card2 = draw_card(user_1, user_2, p1_cards, p2_cards)

        winner = compare_cards(chosen_card1, chosen_card2)

        if winner == "player1":
            print(f"The winner is {user_1}!\n")
            award_cards(p1_cards, chosen_card1, chosen_card2)
            print(f"{user_1}'s deck: {len(p1_cards)}")
            print(f"{user_2}'s deck: {len(p2_cards)}")    


        elif winner == "player2":
            print(f"The winner is {user_2}!\n")
            award_cards(p2_cards, chosen_card1, chosen_card2)
            print(f"{user_2}'s deck: {len(p2_cards)}")
            print(f"{user_1}'s deck: {len(p1_cards)}\n")

            
        else:
            war_pile = []
            war_result = handle_war(user_1, user_2, p1_cards, p2_cards, chosen_card1, chosen_card2, war_pile)

            if war_result == "game_over":
                break
    
    if len(p1_cards) == 0:
        print(f"{user_2} wins the game!")

    elif len(p2_cards) == 0:
        print(f"{user_1} wins the game!")
            
        


if __name__ == "__main__":
    main()
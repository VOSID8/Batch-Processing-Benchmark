from collections import Counter
import pandas as pd
import numpy as np
import time

def main():
    start_time = time.time()

    with open("data/textinput.txt", "r") as f:
        lines = f.readlines()

    words = [word for line in lines for word in line.split()]
    word_counts = Counter(words)

    df = pd.DataFrame(word_counts.items(), columns=["word", "count"])

    # print(df)

    end_time = time.time()
    elapsed_time = end_time - start_time

    print(f"Time taken by Pandas: {elapsed_time:.2f} Seconds")

if __name__ == "__main__":
    main()

use polars::prelude::*;
use std::fs::File;
use std::io::{self, BufRead};
use std::collections::HashMap;
use std::time::Instant;

fn main() -> PolarsResult<()> {
    let start_time = Instant::now();

    let file_path = "data/textinput.txt"; 

    let file = File::open(file_path).expect("Unable to open the file");
    let reader = io::BufReader::new(file);

    let mut word_counts: HashMap<String, usize> = HashMap::new();

    for line in reader.lines() {
        let line = line.expect("Unable to read line");
        for word in line.split_whitespace() {
            *word_counts.entry(word.to_string()).or_insert(0) += 1;
        }
    }

    let words: Vec<&str> = word_counts.keys().map(String::as_str).collect();
    let counts: Vec<u32> = word_counts.values().map(|&count| count as u32).collect();

    let s1 = Column::new("Word".into(), words);
    let s2 = Column::new("Count".into(), counts);

    let df = DataFrame::new(vec![s1, s2])?;

    // println!("{}", df);

    let end_time = Instant::now();
    let duration = end_time.duration_since(start_time);

    println!("Time taken by Polars (Rust): {:?}", duration);

    Ok(())
}

# 📚 LibraTech — Brownfields Java Assessment

## Overview

LibraTech is a small library management system that has been quietly
running in production for years. Its core (`Book`, `Patron`, `Loan`,
`Library`) works, but it has **no automated tests** and it doesn't
know the difference between a paperback, a reference book, and a DVD —
every item gets the same 14-day loan period.

The business now wants that fixed. You are joining this codebase as a
new developer, not starting a new one — this is a **brownfields**
project. That changes how you have to work: you can't just delete and
rewrite what's there, because it's already trusted and already in use.
You have to understand it, protect it with tests, and extend it
carefully.

You'll do this by working through a small sprint backlog, applying
object-oriented design (an interface-based Strategy pattern) to remove
a chunk of duplicated/hard-coded logic, and building the new
functionality as a layer on top of the existing code rather than by
rewriting it.

| Learning Outcome | Where you'll apply it |
|---|---|
| **Testing** | Writing your own characterization tests for untested legacy code; TDD against given tests for new classes |
| **Brownfields** | Working inside an existing, working codebase — extending it safely, without breaking what already works |
| **Agile Methodology** | Working from a sprint backlog of user stories with acceptance criteria, and tracking your progress on it |
| **OOP** | Encapsulation, interfaces, polymorphism (the Strategy pattern), and composition |
| **Systems Design** | Replacing implicit, hard-coded rules with an explicit abstraction (Open/Closed Principle); composing new behaviour instead of editing legacy code |

## Assessment Structure

This assessment has two components. You may complete them in any order.

| Component | Weight | Recommended Time |
|---|---|---|
| Implementation | **50%** | 2 hours |
| Comprehensive Long Question | **50%** | 1 hour |

### Scoring

```
Implementation Score = (Step 1 rubric marks / 10)                × 10%
                      + (tests passed / total auto-graded tests) × 30%
                      + (Step 6 backlog rubric marks / 10)        × 10%

Long Q Score          = (marks earned / total marks)              × 50%

Final Score           = Implementation Score + Long Q Score
```

---

## System Design

```
                    LoanPolicy  (interface)
                         ▲
          ┌──────────────┼──────────────┐
StandardLoanPolicy  ReferenceLoanPolicy  MediaLoanPolicy

Book ────has-a────► LoanPolicy

Library  (legacy, locked)          LibraryManager  (new, composes Library)
 ├─ catalogue: List<Book>            ├─ library: Library
 ├─ patrons:   List<Patron>          └─ fineCalculator: FineCalculator
 └─ loans:     List<Loan>
```

`Library` is the existing brownfield core. `LibraryManager` is new
code that *wraps* `Library` rather than modifying it — this is the
central systems-design decision of the assessment: new behaviour is
added by composition, not by editing trusted legacy code.

---

## Project Structure

```
libra-tech-brownfield/
  pom.xml
  backlog.md                              <- Agile artifact, Step 6
  src/
    main/java/za/co/wethinkcode/
      Main.java                           <- LOCKED, do not modify
      model/
        Book.java                         <- legacy, mostly locked (see Step 3)
        Patron.java                       <- LOCKED, legacy, fully implemented
        Loan.java                         <- LOCKED, legacy, fully implemented
        LoanPolicy.java                   <- interface, LOCKED
        StandardLoanPolicy.java           <- TODO, Step 2
        ReferenceLoanPolicy.java          <- TODO, Step 2
        MediaLoanPolicy.java              <- TODO, Step 2
      service/
        Library.java                      <- LOCKED, legacy, fully implemented
        FineCalculator.java               <- TODO, Step 4
        LibraryManager.java               <- TODO, Step 5
    test/java/za/co/wethinkcode/
      BookTest.java                       <- LOCKED, given
      PatronTest.java                     <- LOCKED, given
      LoanTest.java                       <- LOCKED, given
      LoanPolicyTest.java                 <- LOCKED, given
      FineCalculatorTest.java             <- LOCKED, given
      LibraryManagerTest.java             <- LOCKED, given
      LibraryCharacterizationTest.java    <- YOU WRITE THIS, Step 1
```

> **Do NOT modify any file marked LOCKED, or `Main.java`.** They
> represent either trusted legacy code or the safety net your new code
> is measured against.

---

## Getting Started

Open the project in IntelliJ as a Maven project (`File → Open`, select
the `pom.xml`), let it import, then:

```bash
mvn compile
```

Run the test suite at any point to check your progress:

```bash
mvn test
```

Right now, most tests will fail — that's expected. `Library`,
`Patron`, `Loan`, and the locked half of `Book` are already
implemented; everything else is waiting for you.

---

## Implementation Steps

Work through the steps roughly in order — later ones depend on
earlier ones.

---

### Step 1 — Characterize the Legacy `Library` Class

**File:** `src/test/java/za/co/wethinkcode/LibraryCharacterizationTest.java`

Before changing anything or building on top of `Library`, read it
carefully (`src/main/java/za/co/wethinkcode/service/Library.java`) and
write tests that pin down what it **actually does today** — not what
you think it should do. This is called a **characterization test**: it
protects the current behaviour so that any future change (by you or
someone else) which accidentally breaks it gets caught immediately.

A skeleton with suggested method names is already in the file. Fill
each one in, and add more if you discover other behaviour worth
protecting. At minimum, cover:

- A successful checkout (book becomes unavailable, a `Loan` is created)
- Checking out a book that's already on loan
- Checking out an unknown ISBN or an unknown patron ID
- A successful return (book becomes available again)
- Returning a book with no active loan
- The default loan period when none is specified

This step is marked out of 10, on coverage and clarity rather than an
automated pass count.

---

### Step 2 — Implement the `LoanPolicy` Strategy

**Files:** `StandardLoanPolicy.java`, `ReferenceLoanPolicy.java`, `MediaLoanPolicy.java`

`LoanPolicy` is already defined for you as an interface. Implement the
three concrete policies so they satisfy `LoanPolicyTest`:

| Policy | `loanPeriodDays()` | `dailyFineRate()` | `isBorrowable()` | `policyName()` |
|---|---|---|---|---|
| `StandardLoanPolicy` | 21 | 2.00 | `true` | `"Standard"` |
| `ReferenceLoanPolicy` | 0 | 0.0 | `false` | `"Reference"` |
| `MediaLoanPolicy` | 7 | 5.00 | `true` | `"Media"` |

> **Design hint — Polymorphism:** Because all three implement
> `LoanPolicy`, any code that holds a `LoanPolicy` reference can call
> `loanPeriodDays()` or `dailyFineRate()` without caring which concrete
> class it's talking to. This is what replaces the old approach of
> writing `if (type.equals("reference")) { ... } else if (...)`
> scattered through the codebase — a classic brownfield smell.

---

### Step 3 — Extend `Book` Without Breaking It

**File:** `src/main/java/za/co/wethinkcode/model/Book.java`

`Book` is existing, working legacy code — `BookTest` already covers
its current behaviour and must keep passing exactly as it is. Below a
clearly marked line in the file, you'll add:

1. A private `LoanPolicy` field.
2. A second constructor, `Book(isbn, title, author, loanPolicy)`, that
   calls the existing constructor with `this(...)` and then stores the
   policy.
3. `loanPolicy()` — returns the stored policy, or a new
   `StandardLoanPolicy()` if none was set (so books built with the
   original 3-argument constructor keep working unchanged).
4. `setLoanPolicy(LoanPolicy)`.

This is the essence of brownfield work: the existing public contract
(fields, the original constructor, `isAvailable()`, etc.) must not
change, but you can still grow the class to support a new need.

---

### Step 4 — Implement `FineCalculator`

**File:** `src/main/java/za/co/wethinkcode/service/FineCalculator.java`

Implement `calculateFine(Loan loan, LocalDate today)`:

- If the loan is not overdue as of `today`, the fine is `0.0`.
- Otherwise, the fine is `(days overdue) × (loan.book().loanPolicy().dailyFineRate())`.

Use `Loan`'s existing `isOverdue(LocalDate)` and `daysOverdue(LocalDate)`
methods rather than recalculating the date math yourself — they're
already implemented and tested. `FineCalculatorTest` drives this step;
get it passing.

---

### Step 5 — Implement `LibraryManager`

**File:** `src/main/java/za/co/wethinkcode/service/LibraryManager.java`

`LibraryManager` is the new layer that ties everything together. It
holds a `Library` and a `FineCalculator` **by composition** — it does
not extend `Library` and it does not modify it. This is the safest way
to add behaviour to a brownfield system: the legacy class stays
exactly as it was, proven by Step 1's tests, and new logic lives
beside it.

Implement:

| Method | Details |
|---|---|
| `libraryName()` | Returns the wrapped library's name. |
| `checkoutBook(isbn, patronId, today)` | Looks up the book's `LoanPolicy`; throws `IllegalStateException` if `isBorrowable()` is `false`; otherwise delegates to `library.checkout(isbn, patronId, today, policy.loanPeriodDays())`. |
| `returnBook(isbn, today)` | Delegates to `library.returnBook(isbn, today)`. |
| `calculateFine(loan, today)` | Delegates to the `FineCalculator`. |
| `totalOutstandingFines(today)` | Sums `calculateFine(...)` over `library.activeLoans()`. |
| `overdueLoans(today)` | Filters `library.activeLoans()` to those where `isOverdue(today)` is `true`. |
| `booksCurrentlyOnLoan()` | Returns `library.activeLoans().size()`. |

`LibraryManagerTest` drives this step; get it passing.

---

### Step 6 — Update the Sprint Backlog

**File:** `backlog.md`

Once your implementation satisfies a user story's acceptance criteria,
move that story from **To Do** through **In Progress** to **Done** on
the board in `backlog.md`. When all five stories are Done, write a
short Sprint Retro at the bottom (3–5 sentences: what went well, what
was harder than expected about working in someone else's code, what
you'd do differently next sprint).

This step is marked out of 10, mostly on whether the board reflects
what you actually built and whether the retro shows genuine
reflection rather than a generic paragraph.

---

## Comprehension Questions

Answer all five questions in the `answers.txt` file provided in your
repository. Write in full sentences. Where questions ask for code
examples, keep them short and focused.

---

### Question 1 — Testing **(10 marks)**

A colleague asks you to explain the difference between Test-Driven
Development and characterization testing — the two testing approaches
you used in this assessment. Describe what each is, when you'd reach
for one over the other, and why writing tests *before* touching
existing, untested code is considered good practice in a professional
team.

---

### Question 2 — Brownfields **(10 marks)**

Explain what "brownfield" software development means, how it differs
from "greenfield" development, and what specific risks it introduces
that greenfield work doesn't have. Describe at least two concrete
strategies (not necessarily ones used in this assessment) for safely
changing a legacy system that has little or no test coverage.

---

### Question 3 — Agile Methodology **(10 marks)**

A new team member has only ever worked in a waterfall environment and
doesn't understand why your team plans in sprints with a backlog of
user stories instead of writing a full specification up front.
Explain what a user story and a sprint backlog are, what a
"Definition of Done" is for, and why an iterative approach is
particularly well suited to brownfield work.

---

### Question 4 — OOP **(10 marks)**

In this assessment, the original `Library` class made loan-period
decisions using hard-coded values, and you replaced that with the
`LoanPolicy` interface and three implementing classes. Explain what
the Strategy pattern is, how it relates to the Open/Closed Principle,
and why designing `LoanPolicy` as an interface — rather than adding an
`if/else` chain based on a `String type` field on `Book` — was the
better long-term choice. Use a short code example to support your
explanation.

---

### Question 5 — Systems Design: Technical Reflection **(10 marks)**

Describe the overall design of the system you built in this
assessment from a technical perspective. Your answer should address:
why `LibraryManager` wraps `Library` by composition instead of
extending or editing it; how responsibilities are divided between
`Library`, `LibraryManager`, `FineCalculator`, and `LoanPolicy`; and
how the characterization tests you wrote in Step 1 made the later
steps safer to build.

Do not describe individual method bodies line by line. Focus on the
architectural decisions and why they matter for a system that has to
keep working while it changes.

---

*Read the code that's already there before you write any of your own. 📖*

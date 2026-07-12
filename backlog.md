# Sprint Backlog — LibraTech Loan Policies

**Sprint Goal:** Give the library policy-aware checkout (different loan
periods and fines per book category, with Reference books blocked from
borrowing) and basic reporting, without breaking anything already in
production.

> TODO (Step 6): As you complete each step of the assessment, move the
> matching user story from **To Do** into **In Progress** and then
> **Done**. When every story is Done, write your 3–5 sentence Sprint
> Retro at the bottom.

---

## To Do

- [ ] **US-1**: As a librarian, I want each category of book (Standard,
  Reference, Media) to have its own loan period and daily fine rate,
  so that policy is applied consistently instead of relying on staff
  to remember the rules.
  _Acceptance criteria: `LoanPolicy` and its three implementations
  exist and pass `LoanPolicyTest`._

- [ ] **US-2**: As a librarian, I want to attach a loan policy to a
  book without breaking any existing code that creates books the old
  way, so the rollout is safe.
  _Acceptance criteria: `Book` supports an optional `LoanPolicy`;
  `BookTest` still passes unmodified._

- [ ] **US-3**: As a librarian, I want fines calculated automatically
  based on how overdue a loan is and which policy applies, so I don't
  have to work it out by hand.
  _Acceptance criteria: `FineCalculator` passes `FineCalculatorTest`._

- [ ] **US-4**: As a library manager, I want policy-aware checkout,
  overdue tracking, and a total-fines figure, so I can run the branch
  day-to-day.
  _Acceptance criteria: `LibraryManager` passes `LibraryManagerTest`._

- [ ] **US-5 (spike)**: As a QA engineer, I want characterization
  tests for the existing checkout/return workflow before we build
  anything else on top of it, so a future regression gets caught
  immediately.
  _Acceptance criteria: `LibraryCharacterizationTest` covers, at
  minimum, successful checkout, checkout of an unavailable book,
  checkout of an unknown book/patron, return, return with no active
  loan, and the default loan period._

## In Progress

## Done

---

## Sprint Retro

_TODO: In 3–5 sentences, reflect on the sprint. What went well? What
was harder than expected, and why (e.g. working inside code you didn't
write)? If you ran this sprint again, what would you do differently?_

package org.contextmapper.generated.statcontext.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Ranking.
 */
@Entity
@Table(name = "ranking")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Ranking implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "ranking")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "users", "ranking" }, allowSetters = true)
    private Set<RankingEntry> entries = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Ranking id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<RankingEntry> getEntries() {
        return this.entries;
    }

    public void setEntries(Set<RankingEntry> rankingEntries) {
        if (this.entries != null) {
            this.entries.forEach(i -> i.setRanking(null));
        }
        if (rankingEntries != null) {
            rankingEntries.forEach(i -> i.setRanking(this));
        }
        this.entries = rankingEntries;
    }

    public Ranking entries(Set<RankingEntry> rankingEntries) {
        this.setEntries(rankingEntries);
        return this;
    }

    public Ranking addEntries(RankingEntry rankingEntry) {
        this.entries.add(rankingEntry);
        rankingEntry.setRanking(this);
        return this;
    }

    public Ranking removeEntries(RankingEntry rankingEntry) {
        this.entries.remove(rankingEntry);
        rankingEntry.setRanking(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ranking)) {
            return false;
        }
        return id != null && id.equals(((Ranking) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Ranking{" +
            "id=" + getId() +
            "}";
    }
}
